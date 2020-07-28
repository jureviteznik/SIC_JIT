package sic.sim.addons.jit;

import java.util.HashMap;
import java.util.LinkedList;

import sic.sim.vm.Machine;
import sic.sim.vm.Registers;
import sic.sim.addons.jit.sic2intel.structure.CodeGenerator;
import sic.sim.addons.jit.sic2intel.structure.DataInspector;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstr;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrPROC;
import sic.sim.addons.jit.sic2intel.structure.sic.*;
import sic.sim.addons.jit.lexer.*;

public class JustInTime {
    ///////////////////
    // CLASS OBJECTS //
    ///////////////////
    public final Machine machine;

    /**
     * object that tracks how many times each asm operation has been executed
     * https://stackoverflow.com/a/107987
     */
    HashMap<Integer, MutableInt> freq;
    class MutableInt {
        int value = 1; // note that we start at 1 since we're counting
        public void increment () { ++value;      }
        public int  get ()       { return value; }

    }

    /** CodeGenerator is still a ?... maybe I should just combine all lists of the object (check CodeGenerator) */
    HashMap<Integer, CodeGenerator> translatedBlocks;

    //if a line of code has been executed this many times switch to jit execution 
    int numRepeats = 4;

    /////////////////
    // CONSTRUCTOR //
    /////////////////
    public JustInTime(final Machine m){
        this.machine = m;
        freq = new HashMap<>();
        translatedBlocks = new HashMap<>();
    }

    ///////////////
    // FUNCTIONS //
    ///////////////

    /**https://docs.oracle.com/javase/8/docs/technotes/guides/jni/spec/design.html */
    /**https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaNativeInterface.html */
    private native void helloWorld();
    private native void executeAsm(String asms, String data);

    static {
        //this works if the .dll files are in ../jdk/bin folder
        System.loadLibrary("sic_jit_pkg");

        //this does work but its a bad solution since the path is absolute
        //System.load("C:\\Users\\Uporabnik\\Desktop\\uni\\diplomska\\SicTools-master\\src\\sic\\sim\\addons\\jit\\sic_jit_pkg.dll");
    }

    /**
     * Increments a frequency of the executed address
     */
    public void addFreq(int addr){
        //get the number of times the line has been executed
        MutableInt count = freq.get(addr);
        //if null then this is the first time, add the address of the line to the hashmap
        if (count == null) {
            freq.put(addr, new MutableInt());
        }else {
            count.increment();
            System.out.println("addr " + addr + " has been executed " + count.get() + " times!");

            //check how many times has the line been executed
            if(count.get() >= numRepeats){
                
                //check if the block is already translated
                if(translatedBlocks.containsKey(addr)){
                    //TODO
                        
                //if the block is not yet translated, do it and add it to the map
                }else{
                    Lexer lex = new Lexer(machine, addr);
                    SicProgram sicProg = new SicProgram(lex.convertToIntel());
                    sicProg.instrs.addAll(0, generateRegSetUp(machine.registers));

                    //JUST FOR NOW! - set PC reg to the last instruction converted
                    //TODO: FIX THIS!
                    machine.registers.setPC(lex.finalInstrAddr);
                    
                    /**out print for debugging */
                    System.out.println("..:: SIC INSTRS ::..");
                    for(SicInstr ins: sicProg.instrs){
                        System.out.println(ins.toString());
                    }

                    DataInspector inspector = new DataInspector();
                    sicProg.accept(inspector);
                    
                    CodeGenerator generator = new CodeGenerator();
                    sicProg.accept(generator);

                    translatedBlocks.put(addr, generator);
                    
                    String asmToBeExecuted = "\t.align 32\n";
                    String dataToBeExecuted = "\t.align 32\n";
                    for(IntelInstr instr : CodeGenerator.endMainBlock)
                        asmToBeExecuted += instr.toString();

                    for(IntelInstr instr : CodeGenerator.endDataBlock)
                        dataToBeExecuted += instr.toString();

                    /*
                    what si this? precedures? do we even need this????
                    for (IntelInstrPROC proc : CodeGenerator.endProcBlock) 
                        asmToBeExecuted += proc.toString();
                    */
                    
                    /**out print for debugging */
                    System.out.println("..:: INTEL INSTRS ::..");
                    System.out.println(asmToBeExecuted);
                    System.out.println(dataToBeExecuted);
                    
                    /**JNI function call - executes the translated instructions */
                    this.executeAsm(asmToBeExecuted, dataToBeExecuted);
                }
                
            }
        }
    }

    /**Generates instructions that save values of the curent register state */
    private LinkedList<SicInstr> generateRegSetUp(Registers regs){
        LinkedList<SicInstr> instrs = new LinkedList<SicInstr>();
        instrs.add(new SicInstrLDA(false, 1, regs.getA()+"", false));
        instrs.add(new SicInstrLDX(false, 1, regs.getX()+"", false));
        //instrs.add(new SicInstrLDL(false, 1, regs.getL()+"", false)); - this translates to ebp register, if we override it the stack gets destroyed...
        instrs.add(new SicInstrLDB(false, 1, regs.getB()+"", false));
        instrs.add(new SicInstrLDS(false, 1, regs.getS()+"", false));
        //instrs.add(new SicInstrLDF(false, 1, regs.getF()+"", false)); TODO - this is not how you load float!
        instrs.add(new SicInstrLDT(false, 1, regs.getT()+"", false));
        return instrs;
    }


}