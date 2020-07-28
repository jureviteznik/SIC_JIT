package sic.sim.addons.jit.sic2intel.structure.intel;

import sic.sim.addons.jit.sic2intel.structure.sic.SicInstr;
import sic.sim.addons.jit.sic2intel.structure.sic.SicRegisters;

/**
 * 
 * @author benjamin
 *
 * These instructions shift the bits in the first operand (destination operand) to the left or right by
 * the number of bits specified in the second operand (count operand). Bits shifted beyond the
 * destination operand boundary are first shifted into the CF flag, then discarded. At the end of the
 * shift operation, the CF flag contains the last bit shifted out of the destination operand.
 * 
 * The destination operand can be a register or a memory location. The count operand can be an
 * immediate value or register CL. The count is masked to five bits, which limits the count range
 * to 0 to 31.
 *
 */
public class IntelInstrSHx extends IntelInstr {

	public static final int SHIFTL = 0;   // SHIFTL
	public static final int SHIFTR = 1;   // SHIFTR
	
	private Integer type;
	
	@SuppressWarnings("unused")
	private Integer dstAddrType;
	
	/** 
	 * Destination -- the first operand. 
	 * 
	 * The destination is always a register.
	 * 
	 */
	private Integer dst;
	
	private Integer srcAddrType;
	
	/** 
	 * Source -- the second operand. 
	 * 
	 * The source is always a number.
	 * 
	 */
	private String src;
	
	private SicInstr srcInstr;
	
	/** Arithmetic shift left or right. */
	public IntelInstrSHx(String label, Integer type, Integer dstAddrType, Integer dst, Integer srcAddrType, String src, SicInstr srcInstr) {
		this.label = label;
		this.type = type;
		this.dstAddrType = dstAddrType;
		this.dst = dst;
		this.srcAddrType = srcAddrType;
		this.src = src;
		this.srcInstr = srcInstr;
	}

	@Override
	public String toString() {
		
		// label
		String l = (label != null ? label + ":" : "");// + "\t\t";
		
		// instruction
		String i = "";
		switch(this.type) {
		case IntelInstrSHx.SHIFTL:
			i = "shl";
			break;
		case IntelInstrSHx.SHIFTR:
			i = "shr";
			break;
		}
		
		// operands
		String op = "";
		
		op += IntelRegisters.getActualReg(SicRegisters.toIntel(dst), IntelData.LONG) + //setAddressing(dstAddrType, dst) + 
			  ", " + 
			  setAddressing(srcAddrType, src);
		
		return super.toString(l, i, op, srcInstr);
		// return l + i + op;
	}

}
