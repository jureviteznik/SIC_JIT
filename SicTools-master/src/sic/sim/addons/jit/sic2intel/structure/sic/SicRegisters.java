package sic.sim.addons.jit.sic2intel.structure.sic;

import sic.sim.addons.jit.sic2intel.structure.intel.IntelRegisters;

/**
 * A class that defines the SIC registers as constants.
 * 
 * @author benjamin
 *
 */
public class SicRegisters {

	/**
	 * Register A (accumulator) used mostly for arithmetic operations.
	 */
	public static final int REG_A = 0;

	/**
	 * Register X (index register).
	 */
	public static final int REG_X = 1;

	/**
	 * Register L (linkage register) used by JSUB instruction to store 
	 * return address.
	 */
	public static final int REG_L = 2;
	
	/**
	 * Register B (base register).
	 */
	public static final int REG_B = 3;

	/**
	 * Register S (general-use register).
	 */
	public static final int REG_S = 4;

	/**
	 * Register T (general-use register).
	 */
	public static final int REG_T = 5;

	/**
	 * Register F (float register). 
	 */
	public static final int REG_F = 6;

	/**
	 * PC register (program counter, instruction pointer).
	 */
	public static final int REG_PC = 8;

	/**
	 * SW register (status word).
	 */
	public static final int REG_SW = 9;
	
	/** 
	 * A made up register used mostly as as "middle-man" for indirect 
	 * addressing translation.  
	 */	
	public static final int REG_TMP = 7;
	
	
	/**
	 * Converts a SIC register to an Intel register.
	 * 
	 * @param 	sicReg	SIC register constant
	 * @return			the equivalent Intel register
	 */
	public static Integer toIntel(Integer sicReg) {
		switch(sicReg) {
		case SicRegisters.REG_A:
			return IntelRegisters.REG_A;
		case SicRegisters.REG_B:
			return IntelRegisters.REG_B;
		case SicRegisters.REG_F:
			// top of FPU stack -- ST(0)
			return null;
		case SicRegisters.REG_L:
			return IntelRegisters.REG_BP;
		case SicRegisters.REG_S:
			return IntelRegisters.REG_C;
		case SicRegisters.REG_SW:
			return null;
		case SicRegisters.REG_T:
			return IntelRegisters.REG_D;
		case SicRegisters.REG_X:
			return IntelRegisters.REG_DI;
		case SicRegisters.REG_PC:
			return IntelRegisters.REG_IP;
		case SicRegisters.REG_TMP:
			return IntelRegisters.REG_SI;
		default:
			return null;
		}
	}

	public static String regToString(Integer reg) {
		switch(reg) {
		case SicRegisters.REG_A:
			return "A";
		case SicRegisters.REG_B:
			return "B";
		case SicRegisters.REG_F:
			// top of FPU stack -- ST(0)
			return "F";
		case SicRegisters.REG_L:
			return "L";
		case SicRegisters.REG_S:
			return "S";
		case SicRegisters.REG_SW:
			return "SW";
		case SicRegisters.REG_T:
			return "T";
		case SicRegisters.REG_X:
			return "X";
		case SicRegisters.REG_PC:
			return "PC";
		default:
			return null;
		}
	}
}
