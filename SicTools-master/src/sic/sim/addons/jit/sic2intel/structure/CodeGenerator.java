package sic.sim.addons.jit.sic2intel.structure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import sic.sim.addons.jit.sic2intel.Logger;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstr;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrADD;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrAND;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrCALL;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrCMP;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrDATA;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrDIV;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrEQU;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrFADD;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrFCOM;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrFDIV;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrFILD;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrFIST;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrFLD;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrFMUL;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrFST;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrFSUB;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrIMUL;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrINT;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrJcc;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrMOV;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrMOVZX;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrOR;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrPOP;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrPROC;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrPUSH;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrREAD;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrRET;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrSHx;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrSUB;
import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrWRITE;
import sic.sim.addons.jit.sic2intel.structure.sic.SicAddressing;
import sic.sim.addons.jit.sic2intel.structure.sic.SicData;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrADD;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrADDF;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrADDR;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrAND;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrBYTE;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrCLEAR;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrCOMP;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrCOMPF;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrCOMPR;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrDIV;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrDIVF;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrDIVR;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrEND;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrEQU;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrEQU_BinExpr;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrEQU_UnExpr;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrFIX;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrFLOAT;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrHIO;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrJ;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrJEQ;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrJGT;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrJLT;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrJSUB;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrLDA;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrLDB;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrLDCH;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrLDF;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrLDL;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrLDS;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrLDT;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrLDX;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrLPS;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrMUL;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrMULF;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrMULR;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrNORM;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrOR;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrRD;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrREAD;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrRESB;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrRESW;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrRMO;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrRSUB;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSHIFTL;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSHIFTR;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSIO;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSSK;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSTA;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSTART;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSTB;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSTCH;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSTF;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSTI;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSTL;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSTS;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSTSW;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSTT;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSTX;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSUB;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSUBF;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSUBR;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSVC;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrTD;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrTIO;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrTIX;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrTIXR;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrVisitor;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrWD;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrWORD;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrWRITE;
import sic.sim.addons.jit.sic2intel.structure.sic.SicRegisters;

/**
 * 
 * The visitor responsible for translating the source code.
 * 
 * @author benjamin
 *
 */
public class CodeGenerator implements SicInstrVisitor {
	
	/** Sequence of translated instructions. */
	public static LinkedList<IntelInstr> endMainBlock;
	
	/** Sequence of translated data initialisation instructions. */
	public static LinkedList<IntelInstr> endDataBlock;
	
	/** Sequence of generated procedures. */
	public static LinkedList<IntelInstrPROC> endProcBlock;
	
	/** A hashtable containing variable labels and their respective types. */
	public static HashMap<String, Integer> dataTypes;
	
	/** A hashtable containing variable labels and their respective values. */
	public static HashMap<String, String> dataValues;
	
	/** A hash table containing device names and their respective file descriptors. */
	public static HashMap<String, String> fDescriptors;
	
	/** A set containing names of EQU variables. */
	public static HashSet<String> equSet;
	
	/** Label of the first instruction, optionally indicated by instruction END. */
	public static String startLabel;
	
	/**
	 * Main constructor.
	 */
	public CodeGenerator() {
		endMainBlock = new LinkedList<IntelInstr>();
		endDataBlock = new LinkedList<IntelInstr>();
		endProcBlock = new LinkedList<IntelInstrPROC>();
		
		dataTypes = DataInspector.dataTypes;
		dataValues = DataInspector.dataValues;
		equSet = DataInspector.equSet;
		
		fDescriptors = new HashMap<String, String>();
	}
	
	@Override
	public void visit(SicInstrSTART acceptor) {
		//System.out.println("..:: START - " + acceptor.number + " ::..");
		
		// doesn't translate to anything
	}
	
	@Override
	public void visit(SicInstrEND acceptor) {
		//System.out.println("..:: END ::..");
		
		/*
		 * Syntax: int sys_exit(int status) 
		 * Source: kernel/exit.c 
		 * Action: terminate the current process 
  		 * Details: status is return code
		 */
		
		startLabel = acceptor.identifier;
		
		// int sys_exit(int status): 0x1 --> %eax
		endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_A, SicAddressing.IMMEDIATE, "0x1", false, acceptor));
		// int status: 0x0 --> %ebx
		endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_B, SicAddressing.IMMEDIATE, "0x0", false, null));
		// sys_call: interrupt 0x80
		endMainBlock.add(new IntelInstrINT(null, "0x80", null));
	}
	
	@Override
	public void visit(SicInstrADD acceptor) {
		// System.out.println("..:: ADD ::..");
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, acceptor));
			endMainBlock.add(new IntelInstrADD(null, SicAddressing.NONE, SicRegisters.REG_A, SicAddressing.SIMPLE, SicRegisters.REG_TMP, false, null));
		} else {
			endMainBlock.add(new IntelInstrADD(acceptor.label, SicAddressing.NONE, SicRegisters.REG_A, acceptor.addressing, acceptor.identifier, acceptor.indexed, acceptor));
		}
	}

	@Override
	public void visit(SicInstrADDF acceptor) {
		//System.out.println("..:: ADDF ::..");
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {			
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, acceptor));
			endMainBlock.add(new IntelInstrFADD(null, SicAddressing.SIMPLE, SicRegisters.REG_TMP, false, null));
		} else {
			// FADD
			endMainBlock.add(new IntelInstrFADD(acceptor.label, acceptor.addressing, acceptor.identifier, acceptor.indexed, acceptor));
		}
	}

	@Override
	public void visit(SicInstrADDR acceptor) {
		//System.out.println("..:: ADDR ::..");
		
		// ADDR	 r1, r2 --- r2 <- r2 + r1
		// destination: r2
		// source: r1
		endMainBlock.add(new IntelInstrADD(acceptor.label, SicAddressing.NONE, acceptor.register2, SicAddressing.NONE, acceptor.register1, false, acceptor));
	}

	@Override
	public void visit(SicInstrAND acceptor) {
		//System.out.println("..:: AND ::..");
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, acceptor));
			endMainBlock.add(new IntelInstrAND(null, SicAddressing.NONE, SicRegisters.REG_A, SicAddressing.SIMPLE, SicRegisters.REG_TMP, false, null));
		} else {
			endMainBlock.add(new IntelInstrAND(acceptor.label, SicAddressing.NONE, SicRegisters.REG_A , acceptor.addressing, acceptor.identifier, acceptor.indexed, acceptor));
		}
	}

	@Override
	public void visit(SicInstrCLEAR acceptor) {
		//System.out.println("..:: CLEAR ::..");
		
		endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, acceptor.register, SicAddressing.IMMEDIATE, "0", false, acceptor));
	}

	@Override
	public void visit(SicInstrCOMP acceptor) {
		//System.out.println("..:: COMP ::..");
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, acceptor));
			endMainBlock.add(new IntelInstrCMP(null, SicAddressing.NONE, SicRegisters.REG_A, SicAddressing.SIMPLE, SicRegisters.REG_TMP, false, null));
		} else {
			endMainBlock.add(new IntelInstrCMP(acceptor.label, SicAddressing.NONE, SicRegisters.REG_A, acceptor.addressing, acceptor.identifier, acceptor.indexed, acceptor));
		}
	}

	@Override
	public void visit(SicInstrCOMPF acceptor) {
		//System.out.println("..:: COMPF ::..");
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, acceptor));
			endMainBlock.add(new IntelInstrFCOM(null, SicAddressing.SIMPLE, SicRegisters.REG_TMP, false, null));
		} else if (acceptor.addressing == SicAddressing.IMMEDIATE) {
			// auto generated temporary memory location
			IntelInstrDATA temp = new IntelInstrDATA(SicData.FLOAT, acceptor.identifier);
			dataTypes.put(temp.getLabel(), SicData.FLOAT);
			endDataBlock.addFirst(temp);
			
			endMainBlock.add(new IntelInstrFCOM(null, SicAddressing.SIMPLE, temp.label, false, acceptor));
		} else {
			// FCOM
			endMainBlock.add(new IntelInstrFCOM(acceptor.label, acceptor.addressing, acceptor.identifier, acceptor.indexed, acceptor));
		}
	}

	@Override
	public void visit(SicInstrCOMPR acceptor) {
		//System.out.println("..:: COMPR ::..");
		
		endMainBlock.add(new IntelInstrCMP(acceptor.label, SicAddressing.NONE, acceptor.register1, SicAddressing.NONE, acceptor.register2, false, acceptor));
	}

	@Override
	public void visit(SicInstrDIV acceptor) {
		//System.out.println("..:: DIV ::..");
		
		// store %edx register
		endMainBlock.add(new IntelInstrPUSH(acceptor.label, SicRegisters.REG_T, acceptor));
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, null));
			endMainBlock.add(new IntelInstrDIV(null, SicAddressing.SIMPLE, SicRegisters.REG_TMP, false, null));
		} else if(acceptor.addressing == SicAddressing.IMMEDIATE) {
			// if the operand is an immediate value, first store it to memory and divide by that
			
			// auto generated temporary memory location
			IntelInstrDATA temp = new IntelInstrDATA(SicData.WORD, acceptor.identifier);
			dataTypes.put(temp.getLabel(), SicData.RESW);
			endDataBlock.addFirst(temp);
			
			endMainBlock.add(new IntelInstrDIV(acceptor.label, SicAddressing.SIMPLE, temp.label, false, acceptor));
		} else {
			endMainBlock.add(new IntelInstrDIV(acceptor.label, acceptor.addressing, acceptor.identifier, acceptor.indexed, acceptor));
		}
		
		// restore %edx register
		endMainBlock.add(new IntelInstrPOP(acceptor.label, SicRegisters.REG_T, null));
	}

	@Override
	public void visit(SicInstrDIVF acceptor) {
		//System.out.println("..:: DIVF ::..");
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, acceptor));
			endMainBlock.add(new IntelInstrFDIV(null, SicAddressing.SIMPLE, SicRegisters.REG_TMP, false, null));
		} else {
			// FDIV
			endMainBlock.add(new IntelInstrFDIV(acceptor.label, acceptor.addressing, acceptor.identifier, acceptor.indexed, acceptor));
		}
	}

	@Override
	public void visit(SicInstrDIVR acceptor) {
		//System.out.println("..:: DIVR ::..");
		
		// IntelInstrDATA temp = null;
		
		// store %edx register
		endMainBlock.add(new IntelInstrPUSH(acceptor.label, SicRegisters.REG_T, acceptor));
		
		if(acceptor.register2 != SicRegisters.REG_A) {
			// auto generated temporary memory location
			/*
			temp = new IntelInstrDATA(SicData.RESW, "1");
			dataTypes.put(temp.getLabel(), SicData.RESW);
			endData.addFirst(temp);
			*/
			
			// store contents of reg A to memory
			// endInstrs.add(new IntelInstrMOV(acceptor.label, SicAddressing.SIMPLE, temp.label, SicAddressing.NONE, SicRegisters.REG_A, false));
			endMainBlock.add(new IntelInstrPUSH(acceptor.label, SicRegisters.REG_A, null));
			// move contents of register2 to reg A
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_A, SicAddressing.NONE, acceptor.register2, false, null));
		}
		
		// division
		endMainBlock.add(new IntelInstrDIV(null, SicAddressing.NONE, acceptor.register1, false, null));
		
		if(acceptor.register2 != SicRegisters.REG_A) {
			// move result in reg A to register2
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, acceptor.register2, SicAddressing.NONE, SicRegisters.REG_A, false, null));
			// restore contents of reg A from memory
			// endInstrs.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_A, SicAddressing.SIMPLE, temp.label, false));
			endMainBlock.add(new IntelInstrPOP(null, SicRegisters.REG_A, null));
		}
		
		// restore %edx register
		endMainBlock.add(new IntelInstrPOP(acceptor.label, SicRegisters.REG_T, null));
	}

	@Override
	public void visit(SicInstrFIX acceptor) {
		//System.out.println("..:: FIX ::..");
		
		// auto generated temporary memory location
		IntelInstrDATA temp = new IntelInstrDATA(SicData.RESW, "1");
		dataTypes.put(temp.getLabel(), SicData.RESW);
		endDataBlock.addFirst(temp);
		
		// convert float to integer and store value to memory
		endMainBlock.add(new IntelInstrFIST(acceptor.label, SicAddressing.SIMPLE, temp.getLabel(), false, acceptor));
		// load integer value from memory
		endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_A, SicAddressing.SIMPLE, temp.getLabel(), false, null));
	}

	@Override
	public void visit(SicInstrFLOAT acceptor) {
		//System.out.println("..:: FLOAT ::..");
		
		// auto generated temporary memory location
		IntelInstrDATA temp = new IntelInstrDATA(SicData.RESW, "1");
		dataTypes.put(temp.getLabel(), SicData.RESW);
		endDataBlock.addFirst(temp);
		
		// store integer value from register A to memory
		endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.SIMPLE, temp.getLabel(), SicAddressing.NONE, SicRegisters.REG_A, false, acceptor));
		// load integer value from memory and convert to float
		endMainBlock.add(new IntelInstrFILD(null, SicAddressing.SIMPLE, temp.getLabel(), false, null));
	}

	@Override
	public void visit(SicInstrHIO acceptor) {
		//System.out.println("..:: HIO ::..");
		
		Logger.error("SIC instruction \"HIO\" not supported.");
	}

	@Override
	public void visit(SicInstrJ acceptor) {
		//System.out.println("..:: J ::..");
		
		/*
		 * In case of an infinite loop terminate the program.
		 * 
		 * Infinite loop example:
		 *     halt J halt
		 */
		if(acceptor.label.equals(acceptor.identifier)) {
			/*
			 * Syntax: int sys_exit(int status) 
			 * Source: kernel/exit.c 
			 * Action: terminate the current process 
	  		 * Details: status is return code
			 */
			
			// int sys_exit(int status) -- 1
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_A, SicAddressing.IMMEDIATE, "0x1", false, acceptor));
			// int status -- 0
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_B, SicAddressing.IMMEDIATE, "0x0", false, null));
			// sys_call -- interrupt 0x80
			endMainBlock.add(new IntelInstrINT(null, "0x80", null));
		} else {
			endMainBlock.add(new IntelInstrJcc(acceptor.label, IntelInstrJcc.J, acceptor.identifier, acceptor));
		}
	}

	@Override
	public void visit(SicInstrJEQ acceptor) {
		//System.out.println("..:: JEQ ::..");
		
		endMainBlock.add(new IntelInstrJcc(acceptor.label, IntelInstrJcc.JEQ, acceptor.identifier, acceptor));
	}

	@Override
	public void visit(SicInstrJGT acceptor) {
		//System.out.println("..:: JGT ::..");
		
		endMainBlock.add(new IntelInstrJcc(acceptor.label, IntelInstrJcc.JGT, acceptor.identifier, acceptor));
	}

	@Override
	public void visit(SicInstrJLT acceptor) {
		//System.out.println("..:: JLT ::..");
		
		endMainBlock.add(new IntelInstrJcc(acceptor.label, IntelInstrJcc.JLT, acceptor.identifier, acceptor));
	}

	@Override
	public void visit(SicInstrJSUB acceptor) {
		//System.out.println("..:: JSUB ::..");
		
		endMainBlock.add(new IntelInstrCALL(acceptor.label, acceptor.identifier, acceptor));
	}

	@Override
	public void visit(SicInstrLDA acceptor) {
		//System.out.println("..:: LDA ::..");
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, acceptor));
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_A, SicAddressing.SIMPLE, SicRegisters.REG_TMP, false, null));
		} else {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_A, acceptor.addressing, acceptor.identifier, acceptor.indexed, acceptor));
		}
	}

	@Override
	public void visit(SicInstrLDB acceptor) {
		//System.out.println("..:: LDB ::..");
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, acceptor));
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_B, SicAddressing.SIMPLE, SicRegisters.REG_TMP, false, null));
		} else {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_B, acceptor.addressing, acceptor.identifier, acceptor.indexed, acceptor));
		}
	}

	@Override
	public void visit(SicInstrLDCH acceptor) {
		//System.out.println("..:: LDCH ::..");	
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, acceptor));
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_A, SicAddressing.SIMPLE, SicRegisters.REG_TMP, true, false, null));
			endMainBlock.add(new IntelInstrMOVZX(null, SicAddressing.NONE, SicRegisters.REG_A, SicAddressing.NONE, SicRegisters.REG_A, true, false, acceptor));
		} else {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_A, acceptor.addressing, acceptor.identifier, true, acceptor.indexed, acceptor));
			endMainBlock.add(new IntelInstrMOVZX(null, SicAddressing.NONE, SicRegisters.REG_A, SicAddressing.NONE, SicRegisters.REG_A, true, false, acceptor));
		}
	}

	@Override
	public void visit(SicInstrLDF acceptor) {
		//System.out.println("..:: LDF ::..");
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, acceptor));
			endMainBlock.add(new IntelInstrFLD(null, SicAddressing.SIMPLE, SicRegisters.REG_TMP, false, null));
		} else {
			// FLD
			endMainBlock.add(new IntelInstrFLD(acceptor.label, acceptor.addressing, acceptor.identifier, acceptor.indexed, acceptor));
		}
	}

	@Override
	public void visit(SicInstrLDL acceptor) {
		//System.out.println("..:: LDL ::..");
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, acceptor));
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_L, SicAddressing.SIMPLE, SicRegisters.REG_TMP, false, null));
		} else {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_L, acceptor.addressing, acceptor.identifier, acceptor.indexed, acceptor));
		}
		
		/*
		if (dataTypes.containsKey(acceptor.identifier)) {
			if (dataTypes.get(acceptor.identifier) == SicData.BYTE) {
				endMainBlock.add(new IntelInstrAND(null, SicAddressing.NONE, SicRegisters.REG_L, SicAddressing.IMMEDIATE, "0xFF", false));
			}
		}
		*/
	}

	@Override
	public void visit(SicInstrLDS acceptor) {
		//System.out.println("..:: LDS ::..");
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, acceptor));
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_S, SicAddressing.SIMPLE, SicRegisters.REG_TMP, false, null));
		} else {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_S, acceptor.addressing, acceptor.identifier, acceptor.indexed, acceptor));
		}
	}

	@Override
	public void visit(SicInstrLDT acceptor) {
		//System.out.println("..:: LDT ::..");
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, acceptor));
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_T, SicAddressing.SIMPLE, SicRegisters.REG_TMP, false, null));
		} else {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_T, acceptor.addressing, acceptor.identifier, acceptor.indexed, acceptor));
		}
	}

	@Override
	public void visit(SicInstrLDX acceptor) {
		//System.out.println("..:: LDX ::..");
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, acceptor));
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_X, SicAddressing.SIMPLE, SicRegisters.REG_TMP, false, null));
		} else {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_X, acceptor.addressing, acceptor.identifier, acceptor.indexed, acceptor));
		}
		
		/*
		if (dataTypes.containsKey(acceptor.identifier)) {
			if (dataTypes.get(acceptor.identifier) == SicData.BYTE) {
				endMainBlock.add(new IntelInstrAND(null, SicAddressing.NONE, SicRegisters.REG_X, SicAddressing.IMMEDIATE, "0xFF", false));
			}
		}
		*/
	}

	@Override
	public void visit(SicInstrLPS acceptor) {
		//System.out.println("..:: LPS ::..");
		
		Logger.error("Instruction \"LPS\" not supported.");
	}

	@Override
	public void visit(SicInstrMUL acceptor) {
		//System.out.println("..:: MUL ::..");
		
		// store %edx register
		//endMainBlock.add(new IntelInstrPUSH(acceptor.label, SicRegisters.REG_T));
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, acceptor));
			endMainBlock.add(new IntelInstrIMUL(null, SicAddressing.NONE, SicRegisters.REG_A , SicAddressing.SIMPLE, SicRegisters.REG_TMP, false, null));
		/*	
		} else if(acceptor.addressing == SicAddressing.IMMEDIATE) {
			// if the operand is an immediate value, first store it to memory and divide by that
			
			// auto generated temporary memory location
			IntelInstrDATA temp = new IntelInstrDATA(SicData.WORD, acceptor.identifier);
			dataTypes.put(temp.getLabel(), SicData.RESW);
			endDataBlock.addFirst(temp);
			
			endMainBlock.add(new IntelInstrMUL(null, SicAddressing.SIMPLE, temp.label, false));
		*/
		} else {
			endMainBlock.add(new IntelInstrIMUL(null, SicAddressing.NONE, SicRegisters.REG_A, acceptor.addressing, acceptor.identifier, acceptor.indexed, acceptor));
		}
		
		// restore %edx register
		//endMainBlock.add(new IntelInstrPOP(acceptor.label, SicRegisters.REG_T));
	}

	@Override
	public void visit(SicInstrMULF acceptor) {
		//System.out.println("..:: MULF ::..");
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, acceptor));
			endMainBlock.add(new IntelInstrFMUL(null, SicAddressing.SIMPLE, SicRegisters.REG_TMP, false, null));
		} else {
			// FMUL
			endMainBlock.add(new IntelInstrFMUL(acceptor.label, acceptor.addressing, acceptor.identifier, acceptor.indexed, acceptor));
		}
	}

	@Override
	public void visit(SicInstrMULR acceptor) {
		//System.out.println("..:: MULR ::..");
		
		// IntelInstrDATA temp = null;
		
		/*
		// store %edx register
		endMainBlock.add(new IntelInstrPUSH(acceptor.label, SicRegisters.REG_T));
		
		if(acceptor.register2 != SicRegisters.REG_A) {
			// auto generated temporary memory location
			
			// temp = new IntelInstrDATA(SicData.RESW, "1");
			// dataTypes.put(temp.getLabel(), SicData.RESW);
			// endData.addFirst(temp);
			
			// store contents of reg A to memory
			// endInstrs.add(new IntelInstrMOV(acceptor.label, SicAddressing.SIMPLE, temp.label, SicAddressing.NONE, SicRegisters.REG_A, false));		
			endMainBlock.add(new IntelInstrPUSH(acceptor.label, SicRegisters.REG_A));
			// move contents of register2 to reg A
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_A, SicAddressing.NONE, acceptor.register2, false));
		}
		
		// multiply
		endMainBlock.add(new IntelInstrIMUL(null, SicAddressing.NONE, acceptor.register1, false));
		
		if(acceptor.register2 != SicRegisters.REG_A) {
			// move result in reg A to register2
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, acceptor.register2, SicAddressing.NONE, SicRegisters.REG_A, false));
			// restore contents of reg A from memory
			// endInstrs.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_A, SicAddressing.SIMPLE, temp.label, false));
			endMainBlock.add(new IntelInstrPOP(null, SicRegisters.REG_A));
		}
		
		// restore %edx register
		endMainBlock.add(new IntelInstrPOP(acceptor.label, SicRegisters.REG_T));
		*/
		
		endMainBlock.add(new IntelInstrIMUL(acceptor.label, SicAddressing.NONE, acceptor.register2, SicAddressing.NONE, acceptor.register1, false, acceptor));
	}

	@Override
	public void visit(SicInstrNORM acceptor) {
		//System.out.println("..:: NORM ::..");
		
		Logger.error("Instruction \"NORM\" not supported.");
	}

	@Override
	public void visit(SicInstrOR acceptor) {
		//System.out.println("..:: OR ::..");
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, acceptor));
			endMainBlock.add(new IntelInstrOR(null, SicAddressing.NONE, SicRegisters.REG_A, SicAddressing.SIMPLE, SicRegisters.REG_TMP, false, null));
		} else {
			endMainBlock.add(new IntelInstrOR(acceptor.label, SicAddressing.NONE, SicRegisters.REG_A, acceptor.addressing, acceptor.identifier, acceptor.indexed, acceptor));
		}
	}

	@Override
	public void visit(SicInstrRD acceptor) {
		//System.out.println("..:: RD ::..");
		
		// memory location created to store read char
		IntelInstrDATA temp1 = new IntelInstrDATA(SicData.RESW, "1");
		dataTypes.put(temp1.getLabel(), SicData.RESW);
		endDataBlock.addFirst(temp1);
		
		String deviceNum = acceptor.identifier;
		
		// acquire the real device number
		while (dataValues.containsKey(deviceNum)) {
			deviceNum = dataValues.get(deviceNum);
		}
		if (deviceNum.charAt(0) == 'X') {
			deviceNum = deviceNum.substring(2, deviceNum.length() - 1);
		}
		
		/*
		 * Syntax: ssize_t sys_read(unsigned int fd, char * buf, size_t count) 
		 * Source: fs/read_write.c 
		 * Action: read from a file descriptor 
		 * Details:
		 */
		
		// store registers
		endMainBlock.add(new IntelInstrPUSH(acceptor.label, SicRegisters.REG_A, acceptor));
		endMainBlock.add(new IntelInstrPUSH(null, SicRegisters.REG_B, null));
		endMainBlock.add(new IntelInstrPUSH(null, SicRegisters.REG_S, null));
		endMainBlock.add(new IntelInstrPUSH(null, SicRegisters.REG_T, null));
		
		// ssize_t sys_read(unsigned int fd, char * buf, size_t count): 0x3 --> %eax
		endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_A, SicAddressing.IMMEDIATE, "0x3", false, null));
		
		if (deviceNum.equals("0") || // stdin
			deviceNum.equals("1") || // stdout -- can't use this here (!! READ !!) 
			deviceNum.equals("2")) { // stderr -- can't use this here (!! READ !!) 
			
			// unsigned int fd: file descriptor (only 0) --> %ebx
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_B, SicAddressing.SIMPLE, deviceNum, false, null));
		} else {
			// unsigned int fd: file descriptor stored in memory --> %ebx
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_B, SicAddressing.SIMPLE, fDescriptors.get(deviceNum), false, null));
		}
		
		// const char * buf: address of buffer --> %ecx
		endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_S, SicAddressing.IMMEDIATE, temp1.label, false, null));
		// size_t count: length of buffer --> %edx 
		endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_T, SicAddressing.IMMEDIATE, "0x1", false, null));
		// sys_call: interrupt 0x80
		endMainBlock.add(new IntelInstrINT(null, "0x80", null));
		
		// restore registers
		endMainBlock.add(new IntelInstrPOP(null, SicRegisters.REG_T, null));
		endMainBlock.add(new IntelInstrPOP(null, SicRegisters.REG_S, null));
		endMainBlock.add(new IntelInstrPOP(null, SicRegisters.REG_B, null));
		endMainBlock.add(new IntelInstrPOP(null, SicRegisters.REG_A, null));
		
		endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_A, SicAddressing.SIMPLE, temp1.label, false, null));
	}

	@Override
	public void visit(SicInstrRMO acceptor) {
		//System.out.println("..:: RMO ::..");
		
		endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, acceptor.register2, SicAddressing.NONE, acceptor.register1, false, acceptor));
	}

	@Override
	public void visit(SicInstrRSUB acceptor) {
		//System.out.println("..:: RSUB ::..");
		
		endMainBlock.add(new IntelInstrRET(acceptor.label, null, acceptor));
	}

	@Override
	public void visit(SicInstrSHIFTL acceptor) {
		//System.out.println("..:: SHIFTL ::..");
		
		endMainBlock.add(new IntelInstrSHx(acceptor.label, IntelInstrSHx.SHIFTL, SicAddressing.NONE, acceptor.register, SicAddressing.IMMEDIATE, acceptor.number, acceptor));
	}

	@Override
	public void visit(SicInstrSHIFTR acceptor) {
		//System.out.println("..:: SHIFTR ::..");
		
		endMainBlock.add(new IntelInstrSHx(acceptor.label, IntelInstrSHx.SHIFTR, SicAddressing.NONE, acceptor.register, SicAddressing.IMMEDIATE, acceptor.number, acceptor));
	}

	@Override
	public void visit(SicInstrSIO acceptor) {
		//System.out.println("..:: SIO ::..");
		
		Logger.error("Instruction \"SIO\" not supported.");
	}

	@Override
	public void visit(SicInstrSSK acceptor) {
		//System.out.println("..:: SSK ::..");
		
		Logger.error("Instruction \"SSK\" not supported.");
	}

	@Override
	public void visit(SicInstrSTA acceptor) {
		//System.out.println("..:: STA ::..");
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, acceptor));
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.SIMPLE, SicRegisters.REG_TMP, SicAddressing.NONE, SicRegisters.REG_A, false, null));
		} else {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, acceptor.addressing, acceptor.identifier, SicAddressing.NONE, SicRegisters.REG_A, acceptor.indexed, acceptor));
		}
	}

	@Override
	public void visit(SicInstrSTB acceptor) {
		//System.out.println("..:: STB ::..");
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, acceptor));
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.SIMPLE, SicRegisters.REG_TMP, SicAddressing.NONE, SicRegisters.REG_B, false, null));
		} else {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, acceptor.addressing, acceptor.identifier, SicAddressing.NONE, SicRegisters.REG_B, acceptor.indexed, acceptor));
		}
	}

	@Override
	public void visit(SicInstrSTCH acceptor) {
		//System.out.println("..:: STCH ::..");
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, acceptor));
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.SIMPLE, SicRegisters.REG_TMP, SicAddressing.NONE, SicRegisters.REG_A, true, false, null));
		} else {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, acceptor.addressing, acceptor.identifier, SicAddressing.NONE, SicRegisters.REG_A, true, acceptor.indexed, acceptor));
		}
	}

	@Override
	public void visit(SicInstrSTF acceptor) {
		//System.out.println("..:: STF ::..");
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, acceptor));
			endMainBlock.add(new IntelInstrFST(null, SicAddressing.SIMPLE, SicRegisters.REG_TMP, false, null));
		} else {
			// FST
			endMainBlock.add(new IntelInstrFST(acceptor.label, acceptor.addressing, acceptor.identifier, acceptor.indexed, acceptor));
		}
	}

	@Override
	public void visit(SicInstrSTI acceptor) {
		//System.out.println("..:: STI ::..");
		
		Logger.error("Instruction \"STI\" not supported.");
	}

	@Override
	public void visit(SicInstrSTL acceptor) {
		//System.out.println("..:: STL ::..");
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, acceptor));
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.SIMPLE, SicRegisters.REG_TMP, SicAddressing.NONE, SicRegisters.REG_L, false, null));
		} else {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, acceptor.addressing, acceptor.identifier, SicAddressing.NONE, SicRegisters.REG_L, acceptor.indexed, acceptor));
		}
	}

	@Override
	public void visit(SicInstrSTS acceptor) {
		//System.out.println("..:: STS ::..");
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, acceptor));
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.SIMPLE, SicRegisters.REG_TMP, SicAddressing.NONE, SicRegisters.REG_S, false, null));
		} else {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, acceptor.addressing, acceptor.identifier, SicAddressing.NONE, SicRegisters.REG_S, acceptor.indexed, acceptor));
		}
	}

	@Override
	public void visit(SicInstrSTSW acceptor) {
		//System.out.println("..:: STSW ::..");
		
		Logger.error("Instruction \"STSW\" not supported.");
	}

	@Override
	public void visit(SicInstrSTT acceptor) {
		//System.out.println("..:: STT ::..");
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, acceptor));
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.SIMPLE, SicRegisters.REG_TMP, SicAddressing.NONE, SicRegisters.REG_T, false, null));
		} else {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, acceptor.addressing, acceptor.identifier, SicAddressing.NONE, SicRegisters.REG_T, acceptor.indexed, acceptor));
		}
	}

	@Override
	public void visit(SicInstrSTX acceptor) {
		//System.out.println("..:: STX ::..");
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, acceptor));
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.SIMPLE, SicRegisters.REG_TMP, SicAddressing.NONE, SicRegisters.REG_X, false, null));
		} else {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, acceptor.addressing, acceptor.identifier, SicAddressing.NONE, SicRegisters.REG_X, acceptor.indexed, acceptor));
		}
	}

	@Override
	public void visit(SicInstrSUB acceptor) {
		//System.out.println("..:: SUB ::..");
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, acceptor));
			endMainBlock.add(new IntelInstrSUB(null, SicAddressing.NONE, SicRegisters.REG_A, SicAddressing.SIMPLE, SicRegisters.REG_TMP, false, null));
		} else {
			endMainBlock.add(new IntelInstrSUB(acceptor.label, SicAddressing.NONE , SicRegisters.REG_A, acceptor.addressing, acceptor.identifier, acceptor.indexed, acceptor));
		}
	}

	@Override
	public void visit(SicInstrSUBF acceptor) {
		//System.out.println("..:: SUBF ::..");
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {
			endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, acceptor));
			endMainBlock.add(new IntelInstrFSUB(null, SicAddressing.SIMPLE, SicRegisters.REG_TMP, false, null));
		} else {
			endMainBlock.add(new IntelInstrFSUB(acceptor.label, acceptor.addressing, acceptor.identifier, acceptor.indexed, acceptor));
		}
	}

	@Override
	public void visit(SicInstrSUBR acceptor) {
		//System.out.println("..:: SUBR ::..");
		
		endMainBlock.add(new IntelInstrSUB(acceptor.label, SicAddressing.NONE, acceptor.register2, SicAddressing.NONE, acceptor.register1, false, acceptor));
	}

	@Override
	public void visit(SicInstrSVC acceptor) {
		//System.out.println("..:: SVC ::..");
		
		Logger.error("Instruction \"SVC\" not supported.");
	}

	@Override
	public void visit(SicInstrTD acceptor) {
		//System.out.println("..:: TD ::..");
		
		String deviceNum = acceptor.identifier;
		IntelInstrDATA /*temp1,*/ temp1, temp2;
		
		// acquire the real device number
		while (dataValues.containsKey(deviceNum)) {
			deviceNum = dataValues.get(deviceNum);
		}
		if (deviceNum.charAt(0) == 'X') {
			deviceNum = deviceNum.substring(2, deviceNum.length() - 1);
		}
		
		if (deviceNum.equals("0") || // stdin 
			deviceNum.equals("1") || // stdout
			deviceNum.equals("2")) { // stderr
			// these devices don't need opening because they are always "opened"
			
			
		} else {
			
			// temp1 = new IntelInstrDATA(SicData.RESW, "4");
			
			// memory location to store generated file descriptor
			temp1 = new IntelInstrDATA(SicData.RESW, "1");
			dataTypes.put(temp1.getLabel(), SicData.RESW);
			endDataBlock.addFirst(temp1);
			// filename
			temp2 = new IntelInstrDATA(SicData.STRING, deviceNum + ".txt");
			dataTypes.put(temp2.getLabel(), SicData.STRING);
			endDataBlock.addFirst(temp2);
			
			/*
			 * Syntax: int sys_open(const char * filename, int flags, int mode) 
			 * Source: fs/open.c 
			 * Action: open and possibly create a file or device 
			 * Details: returns a file descriptor number
			 */
			
			// store registers
			endMainBlock.add(new IntelInstrPUSH(acceptor.label, SicRegisters.REG_A, acceptor));
			endMainBlock.add(new IntelInstrPUSH(null, SicRegisters.REG_B, null));
			endMainBlock.add(new IntelInstrPUSH(null, SicRegisters.REG_S, null));
			endMainBlock.add(new IntelInstrPUSH(null, SicRegisters.REG_T, null));
			
			// int sys_open(const char* filename, int flags, int mode): 0x5 --> %eax
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_A, SicAddressing.IMMEDIATE, "0x5", false, null));
			// const char* filename: address of filename string --> %ebx
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_B, SicAddressing.IMMEDIATE, temp2.getLabel(), false, null));
			// int flags: O_CREAT | O_RDWR == 0x66 --> %ecx -- constants defined in fcntl.h
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_S, SicAddressing.IMMEDIATE, "0x66", false, null));
			// int mode: S_IRUSR | S_IWUSR | S_IRGRP | S_IWGRP | S_IROTH | S_IWOTH == 0x1B6 --> %edx -- constants defined in sys/stat.h 
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_T, SicAddressing.IMMEDIATE, "0x1B6", false, null));
			// sys_call: interrupt 0x80
			endMainBlock.add(new IntelInstrINT(null, "0x80", null));
			// store returned FD in %eax to memory temp2
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.SIMPLE, temp1.getLabel(), SicAddressing.NONE, SicRegisters.REG_A, false, null));
			
			// compare %eax with -1
			endMainBlock.add(new IntelInstrCMP(null, SicAddressing.NONE, SicRegisters.REG_A, SicAddressing.IMMEDIATE, "-1", false, null));
			
			fDescriptors.put(deviceNum, temp1.getLabel());
			
			// restore registers
			endMainBlock.add(new IntelInstrPOP(null, SicRegisters.REG_T, null));
			endMainBlock.add(new IntelInstrPOP(null, SicRegisters.REG_S, null));
			endMainBlock.add(new IntelInstrPOP(null, SicRegisters.REG_B, null));
			endMainBlock.add(new IntelInstrPOP(null, SicRegisters.REG_A, null));
		}	
	}

	@Override
	public void visit(SicInstrTIO acceptor) {
		//System.out.println("..:: TIO ::..");
		
		Logger.error("Instruction \"TIO\" not supported.");
	}

	@Override
	public void visit(SicInstrTIX acceptor) {
		//System.out.println("..:: TIX ::..");
		
		// X = X + 1
		endMainBlock.add(new IntelInstrADD(acceptor.label, SicAddressing.NONE, SicRegisters.REG_X, SicAddressing.IMMEDIATE, "1", false, acceptor));
		
		if (acceptor.addressing == SicAddressing.INDIRECT) {
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_TMP, SicAddressing.SIMPLE, acceptor.identifier, false, null));
			endMainBlock.add(new IntelInstrCMP(null, SicAddressing.NONE, SicRegisters.REG_X, SicAddressing.SIMPLE, SicRegisters.REG_TMP, false, null));
		} else {
			// CMP X, m
			endMainBlock.add(new IntelInstrCMP(null, SicAddressing.NONE, SicRegisters.REG_X, acceptor.addressing, acceptor.identifier, acceptor.indexed, null));
		}
	}

	@Override
	public void visit(SicInstrTIXR acceptor) {
		//System.out.println("..:: TIXR ::..");
		
		// X = X + 1
		endMainBlock.add(new IntelInstrADD(acceptor.label, SicAddressing.NONE, SicRegisters.REG_X, SicAddressing.IMMEDIATE, "1", false, acceptor));
		// CMP X, REG
		endMainBlock.add(new IntelInstrCMP(null, SicAddressing.NONE, SicRegisters.REG_X, SicAddressing.NONE, acceptor.register, false, null));
	}

	@Override
	public void visit(SicInstrWD acceptor) {
		//System.out.println("..:: WD ::..");
		
		// memory location created to store char in %eax
		IntelInstrDATA temp1 = new IntelInstrDATA(SicData.RESW, "1");
		dataTypes.put(temp1.getLabel(), SicData.RESW);
		endDataBlock.addFirst(temp1);
		
		String deviceNum = acceptor.identifier;
		
		// acquire the real device number
		while (dataValues.containsKey(deviceNum)) {
			deviceNum = dataValues.get(deviceNum);
		}
		if (deviceNum.startsWith("X'") && deviceNum.endsWith("'")) {
			deviceNum = deviceNum.substring(2, deviceNum.length() - 1);
		}
		
		/*
		 * Syntax: ssize_t sys_write(unsigned int fd, const char * buf, size_t count) 
		 * Source: fs/read_write.c 
		 * Action: write to a file descriptor 
		 * Details:
		 */
		
		endMainBlock.add(new IntelInstrMOV(acceptor.label, SicAddressing.SIMPLE, temp1.label, SicAddressing.NONE, SicRegisters.REG_A, false, acceptor));
		
		// store registers
		endMainBlock.add(new IntelInstrPUSH(null, SicRegisters.REG_A, null));
		endMainBlock.add(new IntelInstrPUSH(null, SicRegisters.REG_B, null));
		endMainBlock.add(new IntelInstrPUSH(null, SicRegisters.REG_S, null));
		endMainBlock.add(new IntelInstrPUSH(null, SicRegisters.REG_T, null));
		
		// ssize_t sys_write(unsigned int fd, const char * buf, size_t count): 0x4 --> %eax
		endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_A, SicAddressing.IMMEDIATE, "0x4", false, null));
		
		if (deviceNum.equals("0") || // stdin -- can't use stdin here (!! WRITE !!) 
			deviceNum.equals("1") || // stdout
			deviceNum.equals("2")) { // stderr
			
			// unsigned int fd: file descriptor (1 or 2) --> %ebx
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_B, SicAddressing.SIMPLE, deviceNum, false, null));
		} else {
			// unsigned int fd: file descriptor stored in memory --> %ebx
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_B, SicAddressing.SIMPLE, fDescriptors.get(deviceNum), false, null));
		}
		
		// const char * buf: address of buffer --> %ecx
		endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_S, SicAddressing.IMMEDIATE, temp1.label, false, null));
		// size_t count: length oof buffer --> %edx 
		endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_T, SicAddressing.IMMEDIATE, "0x1", false, null));
		// sys_call: interrupt 0x80
		endMainBlock.add(new IntelInstrINT(null, "0x80", null));
		
		// restore registers
		endMainBlock.add(new IntelInstrPOP(null, SicRegisters.REG_T, null));
		endMainBlock.add(new IntelInstrPOP(null, SicRegisters.REG_S, null));
		endMainBlock.add(new IntelInstrPOP(null, SicRegisters.REG_B, null));
		endMainBlock.add(new IntelInstrPOP(null, SicRegisters.REG_A, null));
	}

	@Override
	public void visit(SicInstrRESB acceptor) {
		//System.out.println("..:: RESB ::..");
		
		endDataBlock.add(new IntelInstrDATA(acceptor.label, SicData.RESB, acceptor.value, acceptor));
	}

	@Override
	public void visit(SicInstrRESW acceptor) {
		//System.out.println("..:: RESW ::..");
		
		endDataBlock.add(new IntelInstrDATA(acceptor.label, SicData.RESW, acceptor.value, acceptor));
	}

	@Override
	public void visit(SicInstrBYTE acceptor) {
		//System.out.println("..:: BYTE ::..");
		
		endDataBlock.add(new IntelInstrDATA(acceptor.label, SicData.BYTE, acceptor.value, acceptor));
	}

	@Override
	public void visit(SicInstrWORD acceptor) {
		//System.out.println("..:: WORD ::..");
		
		endDataBlock.add(new IntelInstrDATA(acceptor.label, SicData.WORD, acceptor.value, acceptor));
	}
	
	@Override
	public void visit(SicInstrEQU acceptor) {
		//System.out.println("..:: EQU ::..");
		
		endDataBlock.add(new IntelInstrEQU(acceptor.label, acceptor.expr, acceptor));
	}

	@Override
	public void visit(SicInstrWRITE acceptor) {

		IntelInstrWRITE writeProc = null;
		
		// check if a WRITE procedure already exists
		for(IntelInstrPROC proc : endProcBlock) {
			
			// if it exists, use it
			if (proc instanceof IntelInstrWRITE) {
				writeProc = (IntelInstrWRITE) proc;
				break;
			}
		}
		
		String procLabel;
		
		// if there is no existing WRITE procedure, create it
		if (writeProc == null) {
			procLabel = IntelInstrPROC.genLabel();
			endProcBlock.add(new IntelInstrWRITE(procLabel));
		} else {
			procLabel = writeProc.label;
		}
		
		endMainBlock.add(new IntelInstrPUSH(acceptor.label, SicRegisters.REG_S, acceptor));
		endMainBlock.add(new IntelInstrPUSH(null, SicRegisters.REG_T, null));
		
		// WRITE(Var, Len)
		if (acceptor.length == null) {
			
			// create a .string variable, containing the string that will be written
			IntelInstrDATA temp1 = new IntelInstrDATA(SicData.STRING, acceptor.identifier);
			dataTypes.put(temp1.getLabel(), SicData.STRING);
			endDataBlock.add(temp1);
		
			// create an .equ value, defining the length of the before mentioned string
			IntelInstrEQU temp2 = new IntelInstrEQU(new SicInstrEQU_BinExpr(SicInstrEQU_BinExpr.MINUS, new SicInstrEQU_UnExpr(SicInstrEQU_UnExpr.STAR), new SicInstrEQU_UnExpr(SicInstrEQU_UnExpr.ID, temp1.label)));
			equSet.add(temp2.label);
			endDataBlock.add(temp2);

			// put the string address and it's length into registers S (ecx) and T (edx)
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_S, SicAddressing.IMMEDIATE, temp1.label, false, null));
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_T, SicAddressing.SIMPLE, temp2.label, false, null));
		} 
		
		// WRITE("some_text")
		else {
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_S, SicAddressing.IMMEDIATE, acceptor.identifier, false, null));
			endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_T, SicAddressing.IMMEDIATE, acceptor.length, false, null));
		}
		
		endMainBlock.add(new IntelInstrCALL(null, procLabel, null));
		
		endMainBlock.add(new IntelInstrPOP(null, SicRegisters.REG_T, null));
		endMainBlock.add(new IntelInstrPOP(null, SicRegisters.REG_S, null));
	}
	
	@Override
	public void visit(SicInstrREAD acceptor) {
		
		IntelInstrREAD readProc = null;
		
		for(IntelInstrPROC proc : endProcBlock) {
			if (proc instanceof IntelInstrREAD) {
				readProc = (IntelInstrREAD) proc;
				break;
			}
		}
		
		String procLabel;
		
		if (readProc == null) {
			procLabel = IntelInstrPROC.genLabel();
			endProcBlock.add(new IntelInstrREAD(procLabel));
		} else {
			procLabel = readProc.label;
		}
		
		endMainBlock.add(new IntelInstrPUSH(acceptor.label, SicRegisters.REG_S, acceptor));
		endMainBlock.add(new IntelInstrPUSH(null, SicRegisters.REG_T, null));
		
		endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_S, SicAddressing.IMMEDIATE, acceptor.identifier, false, null));
		endMainBlock.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_T, SicAddressing.IMMEDIATE, acceptor.length, false, null));
		
		endMainBlock.add(new IntelInstrCALL(null, procLabel, null));
		
		endMainBlock.add(new IntelInstrPOP(null, SicRegisters.REG_T, null));
		endMainBlock.add(new IntelInstrPOP(null, SicRegisters.REG_S, null));
	}
}