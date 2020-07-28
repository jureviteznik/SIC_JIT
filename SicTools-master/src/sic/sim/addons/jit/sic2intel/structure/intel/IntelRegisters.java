package sic.sim.addons.jit.sic2intel.structure.intel;

/**
 * A class that defines the Intel registers as constants.
 *  
 * @author benjamin
 *
 */
public class IntelRegisters {

	public static final int REG_A = 0;
	
	public static final int REG_B = 1;
	
	public static final int REG_C = 2;
	
	public static final int REG_D = 3;
	
	public static final int REG_SI = 4;
	
	public static final int REG_DI = 5;
	
	public static final int REG_SP = 6;
	
	public static final int REG_BP = 7;
	
	public static final int REG_IP = 8;

	/**changed by Jure Viteznik */
	public static String getActualReg(int baseReg, int dataSize) {
		switch (baseReg) {
		case IntelRegisters.REG_A:

			if (dataSize == IntelData.BYTE) {
				return "al";
			} else if (dataSize == IntelData.LONG) {
				return "eax";
			}

		case IntelRegisters.REG_B:

			if (dataSize == IntelData.BYTE) {
				return "bl";
			} else if (dataSize == IntelData.LONG) {
				return "ebx";
			}
			
		case IntelRegisters.REG_C:
			
			if (dataSize == IntelData.BYTE) {
				return "cl";
			} else if (dataSize == IntelData.LONG) {
				return "ecx";
			}
			
		case IntelRegisters.REG_D:
			
			if (dataSize == IntelData.BYTE) {
				return "dl";
			} else if (dataSize == IntelData.LONG) {
				return "edx";
			}
			
		case IntelRegisters.REG_SI:
			
			//if (dataSize == IntelData.BYTE) {
			//	return "null";
			//} else if (dataSize == IntelData.LONG) {
				return "esi";
			//}
			
		case IntelRegisters.REG_DI:

			//if (dataSize == IntelData.BYTE) {
			//	return "null";
			//} else if (dataSize == IntelData.LONG) {
				return "edi";
			//}
			
		case IntelRegisters.REG_SP:
			return null;	
			
		case IntelRegisters.REG_BP:
			
			//if (dataSize == IntelData.BYTE) {
			//	return "null";
			//} else if (dataSize == IntelData.LONG) {
				return "ebp";
			//}	
			
		case IntelRegisters.REG_IP:
			
			//if (dataSize == IntelData.BYTE) {
			//	return "null";
			//} else if (dataSize == IntelData.LONG) {
				return "eip";
			//}
			
		default:
			return "null";
		}
	}

	/**PRE CHANGE
	 * public static String getActualReg(int baseReg, int dataSize) {
		switch (baseReg) {
		case IntelRegisters.REG_A:

			if (dataSize == IntelData.BYTE) {
				return "%al";
			} else if (dataSize == IntelData.LONG) {
				return "%eax";
			}

		case IntelRegisters.REG_B:

			if (dataSize == IntelData.BYTE) {
				return "%bl";
			} else if (dataSize == IntelData.LONG) {
				return "%ebx";
			}
			
		case IntelRegisters.REG_C:
			
			if (dataSize == IntelData.BYTE) {
				return "%cl";
			} else if (dataSize == IntelData.LONG) {
				return "%ecx";
			}
			
		case IntelRegisters.REG_D:
			
			if (dataSize == IntelData.BYTE) {
				return "%dl";
			} else if (dataSize == IntelData.LONG) {
				return "%edx";
			}
			
		case IntelRegisters.REG_SI:
			
			//if (dataSize == IntelData.BYTE) {
			//	return "null";
			//} else if (dataSize == IntelData.LONG) {
				return "%esi";
			//}
			
		case IntelRegisters.REG_DI:

			//if (dataSize == IntelData.BYTE) {
			//	return "null";
			//} else if (dataSize == IntelData.LONG) {
				return "%edi";
			//}
			
		case IntelRegisters.REG_SP:
			return null;	
			
		case IntelRegisters.REG_BP:
			
			//if (dataSize == IntelData.BYTE) {
			//	return "null";
			//} else if (dataSize == IntelData.LONG) {
				return "%ebp";
			//}	
			
		case IntelRegisters.REG_IP:
			
			//if (dataSize == IntelData.BYTE) {
			//	return "null";
			//} else if (dataSize == IntelData.LONG) {
				return "%eip";
			//}
			
		default:
			return "null";
		}
	}
	 */

}
