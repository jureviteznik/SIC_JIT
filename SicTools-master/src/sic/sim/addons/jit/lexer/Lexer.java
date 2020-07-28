package sic.sim.addons.jit.lexer;

import sic.sim.vm.Machine;
import sic.disasm.Disassembler;

import java.util.HashMap;
import java.util.LinkedList;

import sic.common.Opcode;
import sic.ast.Program;
import sic.ast.instructions.Instruction;
import sic.ast.instructions.InstructionF2n;
import sic.ast.instructions.InstructionF2r;
import sic.ast.instructions.InstructionF2rn;
import sic.ast.instructions.InstructionF2rr;
import sic.ast.instructions.InstructionF34Base;
import sic.common.Flags;
import sic.common.Mnemonics;
import sic.sim.addons.jit.sic2intel.structure.sic.*;
import sic.sim.views.DisassemblyView;

public class Lexer {
    ///////////////////
    // CLASS OBJECTS //
    ///////////////////
    private final Machine machine;
    private Disassembler disassembler;
    //private Program program;

    /** current address */
    private int currAddr;

    private class Var{
        int addr; // kinda useless i guess, since they key of the hashmap is also addr
        String name;
        int val;

        public Var(int a, String n, int v){
            addr = a;
            name = n;
            val = v;
        }
    }
    /** maps of detected variables and labels */
    HashMap<Integer,Var> vars;
    HashMap<Integer, String> labs;

    /** address of the final instruction of the code block */
    public int finalInstrAddr;

    //HashMap<Integer, Symbol> labelMap;

    /////////////////
    // CONSTRUCTOR //
    /////////////////
    public Lexer(final Machine machine, int startAddr) {
        this.machine = machine;
        this.disassembler = new Disassembler(new Mnemonics(), machine);
        this.disassembler.gotoPC();

        this.currAddr = startAddr;
        this.vars = new HashMap<Integer,Var>();
        this.labs = new HashMap<Integer,String>();

        //this.labelMap = DisassemblyView.getLabelMap();
    }

    ///////////////
    // FUNCTIONS //
    ///////////////

    //TODO add support for char!!!

    /**
     * Function atteches labels to the list of instructions
     * @param instrs    list of instructions to whitch we add labels
     * @param startAddress  value of the first address of the block
     */
    private void addLabels(LinkedList<SicInstr> instrs, int startAddress){
        int addr = startAddress;

        for(SicInstr i: instrs){
            if(labs.containsKey(addr)){
                i.label = labs.get(addr);
                labs.remove(addr);
            }
            if(i instanceof SicInstrF34){
                if(((SicInstrF34) i).extended)
                    addr += 4;
                else
                    addr += 3;
            }else if(i instanceof SicInstrF2){
                addr += 2;
            }else if(i instanceof SicInstrF1){
                addr += 1;
            }else{
                System.out.println("Error! Unknown instruction format! (when adding labels)");
            }
        }

    }

    private String setLabel(int addr){
        vars.remove(addr);
        
        if(!labs.containsKey(addr))
            labs.put(addr, "L"+labs.size());
        
        return labs.get(addr);
    }

    /**
     * function returns the label of an address
     * if the address has no label it generates one
     */
    private String getVarLabel(int addr){
        if(!vars.containsKey(addr))
            vars.put(addr, new Var(addr, "V"+vars.size(), machine.memory.getWordRaw(addr)));

        return vars.get(addr).name;
    }

    private LinkedList<SicInstr> generateVarDecl(){
        LinkedList<SicInstr> instrs = new LinkedList<SicInstr>();
        for(Var v: vars.values()){
            SicInstr instr = new SicInstrWORD(v.val+"");
            instr.label = v.name;
            instrs.add(instr);
        }
        return instrs;
    }


    /**
     * returns addressing type of a command based on its flags
     * @param f flags of a command - nixbpe bits
     * @return integer value which coresponds to the addressing type
     * 0-simple, 1-immediate, 2-indirect, -1-wrong input
     */
    private int getAddressing(Flags f){
        if(f.isSimple())
            return 0;
        if(f.isImmediate())
            return 1;
        if(f.isIndirect())
            return 2;
        return -1;
    }

    /**lexify 2.0 */
    public LinkedList<SicInstr> convertToIntel(){
        //return val
        LinkedList<SicInstr> instrs = new LinkedList<SicInstr>();
        
        int startLoc = disassembler.location();

        boolean sawJumpToFirstInstr = false;
        int blockSize = 0;
        while(!sawJumpToFirstInstr){
            Instruction cmd = disassembler.disassemble(startLoc + blockSize);

            switch(cmd.mnemonic.opcode){
                case Opcode.FLOAT:
                    instrs.add(new SicInstrFLOAT()); 
                    break;
                case Opcode.FIX:
                    instrs.add(new SicInstrFIX());
                    break;
                default:{
                    //format 2
                    int r1 = -1;
                    int r2 = -1;

                    if(cmd instanceof InstructionF2n){
                        InstructionF2n f2n = (InstructionF2n) cmd;
                        r1 = f2n.number;
                    }else if(cmd instanceof InstructionF2r){
                        InstructionF2r f2r = (InstructionF2r) cmd;
                        r1 = f2r.register;
                    }else if(cmd instanceof InstructionF2rn){
                        InstructionF2rn f2rn = (InstructionF2rn) cmd;
                        r1 = f2rn.register;
                        r2 = f2rn.number;
                    }else if(cmd instanceof InstructionF2rr){
                        InstructionF2rr f2rr = (InstructionF2rr) cmd;
                        r1 = f2rr.register1;
                        r2 = f2rr.register2;
                    }

                    switch(cmd.mnemonic.opcode){
                        case Opcode.ADDR:
                            instrs.add(new SicInstrADDR(r1, r2));
                            break;
                        case Opcode.SUBR:
                            instrs.add(new SicInstrSUBR(r1, r2));
                            break;
                        case Opcode.MULR:
                            instrs.add(new SicInstrMULR(r1, r2));
                            break;
                        case Opcode.DIVR:
                            instrs.add(new SicInstrDIVR(r1, r2));
                            break;
                        case Opcode.COMPR:
                            instrs.add(new SicInstrCOMPR(r1, r2));
                            break;
                        case Opcode.SHIFTL:
                            //MAYBE NAPAKA!!!! KAKSN MORA BIT TA STRING?
                            instrs.add(new SicInstrSHIFTL(r1, r2 + ""));
                            break;
                        case Opcode.SHIFTR:
                            instrs.add(new SicInstrSHIFTR(r1, r2 + ""));
                            break;
                        case Opcode.RMO:
                            instrs.add(new SicInstrRMO(r1, r2));
                            break;
                        case Opcode.CLEAR:
                            instrs.add(new SicInstrCLEAR(r1));
                            break;
                        case Opcode.TIXR:
                            instrs.add(new SicInstrTIXR(r1));
                            break;
                        default:{

                            InstructionF34Base cmd34 = (InstructionF34Base) cmd;

                            byte[] rawCode = cmd34.emitRawCode();
                            Flags flags = new Flags((int)rawCode[0], (int)rawCode[1]);
                            //Flags flags = ((InstructionF34Base) cmd).flags;

                            int addressing = getAddressing(flags);
                            String operand = "";

                            switch(addressing){
                                //simple
                                case 0:
                                    operand = getVarLabel(cmd34.resolveOperandAddress(startLoc + blockSize));
                                    break;
                                //immediate
                                case 1:
                                    operand = cmd34.getOperandAsString();
                                    break;
                                //indirect - not suportted!
                                case 2:
                                    System.out.println("Indirect addressing is not suported!");
                                    return null;
                                default:
                                    System.out.println("Unknown addressing type!");
                                    return null;
                            }

                            //format 3/4
                            switch(cmd34.mnemonic.opcode){
                            //store
                            case Opcode.STA:
                                instrs.add(new SicInstrSTA(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            case Opcode.STX:
                                instrs.add(new SicInstrSTX(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            case Opcode.STL:
                                instrs.add(new SicInstrSTL(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            case Opcode.STCH:
                                instrs.add(new SicInstrSTCH(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            case Opcode.STB:
                                instrs.add(new SicInstrSTB(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            case Opcode.STS:
                                instrs.add(new SicInstrSTS(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            case Opcode.STF:
                                instrs.add(new SicInstrSTF(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            case Opcode.STT:
                                instrs.add(new SicInstrSTT(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            case Opcode.STSW:
                                instrs.add(new SicInstrSTSW(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            //jumps
                            case Opcode.JEQ:{
                                //get addr to whitch we jump to
                                int jumpAddr = cmd34.resolveOperandAddress(startLoc + blockSize);
                                //save the addr so that we can add a label to it later
                                //and rewrite operand, because otherwise it would point to a variable that does not exist 
                                operand = setLabel(jumpAddr);
                                //if the jump jumps to the first instruction of the block then we end the jit compilation
                                if(jumpAddr == startLoc)
                                    sawJumpToFirstInstr = true;
                                instrs.add(new SicInstrJEQ(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            }
                            case Opcode.JGT:{
                                int jumpAddr = cmd34.resolveOperandAddress(startLoc + blockSize);
                                operand = setLabel(jumpAddr);
                                if(jumpAddr == startLoc)
                                    sawJumpToFirstInstr = true;
                                instrs.add(new SicInstrJGT(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            }
                            case Opcode.JLT:{
                                int jumpAddr = cmd34.resolveOperandAddress(startLoc + blockSize);
                                operand = setLabel(jumpAddr);
                                if(jumpAddr == startLoc)
                                    sawJumpToFirstInstr = true;
                                instrs.add(new SicInstrJLT(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            }
                            case Opcode.J:{
                                int jumpAddr = cmd34.resolveOperandAddress(startLoc + blockSize);
                                operand = setLabel(jumpAddr);
                                if(jumpAddr == startLoc)
                                    sawJumpToFirstInstr = true;
                                instrs.add(new SicInstrJ(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            }
                            //TODO: IDK EXPERIMENT WITH THIS.......
                            case Opcode.RSUB:
                                instrs.add(new SicInstrRSUB(flags.isExtended()));
                                break;
                            case Opcode.JSUB:
                                //setLabel(op);
                                instrs.add(new SicInstrJSUB(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            //load
                            case Opcode.LDA:
                                instrs.add(new SicInstrLDA(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            case Opcode.LDX:
                                instrs.add(new SicInstrLDX(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            case Opcode.LDL:
                                instrs.add(new SicInstrLDL(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            case Opcode.LDCH:
                                instrs.add(new SicInstrLDCH(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            case Opcode.LDB:
                                instrs.add(new SicInstrLDB(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            case Opcode.LDS:
                                instrs.add(new SicInstrLDS(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            case Opcode.LDF:
                                instrs.add(new SicInstrLDF(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            case Opcode.LDT:
                                instrs.add(new SicInstrLDT(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            //aritmetics
                            case Opcode.ADD:
                                instrs.add(new SicInstrADD(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            case Opcode.SUB:
                                instrs.add(new SicInstrSUB(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            case Opcode.MUL:
                                instrs.add(new SicInstrMUL(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            case Opcode.DIV:
                                instrs.add(new SicInstrDIV(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            case Opcode.AND:
                                instrs.add(new SicInstrAND(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            case Opcode.OR:
                                instrs.add(new SicInstrOR(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            case Opcode.COMP:
                                instrs.add(new SicInstrCOMP(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            case Opcode.TIX:
                                instrs.add(new SicInstrTIX(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            //IO
                            case Opcode.RD:
                                instrs.add(new SicInstrRD(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            case Opcode.WD:
                                instrs.add(new SicInstrWD(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            case Opcode.TD:
                                instrs.add(new SicInstrTD(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            //float aritmetics
                            case Opcode.ADDF:
                                instrs.add(new SicInstrADDF(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            case Opcode.SUBF:
                                instrs.add(new SicInstrSUBF(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            case Opcode.MULF:
                                instrs.add(new SicInstrMULF(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            case Opcode.DIVF:
                                instrs.add(new SicInstrDIVF(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            case Opcode.COMPF:
                                instrs.add(new SicInstrCOMPF(flags.isExtended(), addressing, operand, flags.isIndexed()));
                                break;
                            default:
                                System.out.println("oops... nothing to be done");
                            }
                        }//default F2
                    }//switch F2
                }//default F1
            }//switch F1

            blockSize += cmd.size();
        }
        //set final addr
        finalInstrAddr = startLoc + blockSize;
        //add jump labels
        addLabels(instrs, startLoc);
        //add all variables that are used
        instrs.addAll(0, generateVarDecl());
        return instrs;
    }


}