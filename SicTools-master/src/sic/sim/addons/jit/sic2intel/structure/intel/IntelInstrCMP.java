package sic.sim.addons.jit.sic2intel.structure.intel;

import sic.sim.addons.jit.sic2intel.structure.sic.SicInstr;
import sic.sim.addons.jit.sic2intel.structure.sic.SicRegisters;

/**
 * 
 * @author benjamin
 *
 * This instruction compares the first source operand with the second source operand and sets the
 * status flags in the EFLAGS register according to the results. The comparison is performed by
 * subtracting the second operand from the first operand and then setting the status flags in the
 * same manner as the SUB instruction. When an immediate value is used as an operand, it is sign-
 * extended to the length of the first operand.
 *
 */
public class IntelInstrCMP extends IntelInstr {

	private Integer dstAddrType;
	
	/** 
	 * Destination -- the first operand
	 *
	 * The destination is always a register
	 *
	 */
	private Integer dstReg;
	
	private Integer srcAddrType;
	
	/** 
	 * Source -- the second operand
	 *
	 * A memory location or an immediate operand when using a COMP or COMPF instruction.
	 *
	 */
	private String src;
	
	/** 
	 * Source -- the second operand
	 *
	 * A register when using an ADDR instruction.
	 *
	 */
	private Integer srcReg;
	
	private boolean indexed;
	
	private SicInstr srcInstr;
	
	/** compares the values of source and destination register and stores the result in EFLAGS register */
	public IntelInstrCMP(String label, Integer dstAddrType, Integer dstReg, Integer srcAddrType, String src, boolean indexed, SicInstr srcInstr) {
		this.label = label;
		this.dstAddrType = dstAddrType;
		this.dstReg = dstReg;
		this.srcAddrType = srcAddrType;
		this.src = src;
		this.indexed = indexed;
		this.srcInstr = srcInstr;
	}
	
	/** Compares the values of source register and destination register and stores the result in EFLAGS register */
	public IntelInstrCMP(String label, Integer dstAddrType, Integer dstReg, Integer srcAddrType, Integer srcReg, boolean indexed, SicInstr srcInstr) {
		this.label = label;
		this.dstAddrType = dstAddrType;
		this.dstReg = dstReg;
		this.srcAddrType = srcAddrType;
		this.srcReg = srcReg;
		this.indexed = indexed;
		this.srcInstr = srcInstr;
	}

	@Override
	public String toString() {
		// label
		String l = (label != null ? label + ":" : "");// + "\t\t";
		
		// instruction
		String i = "cmp";// + "\t\t";
		
		// operands
		String op = "";
		
		if(srcReg != null) {
			
			op = setAddressing(dstAddrType, IntelRegisters.getActualReg(SicRegisters.toIntel(dstReg), IntelData.LONG)) + 
				 ", " + 
				 setAddressing(srcAddrType, IntelRegisters.getActualReg(SicRegisters.toIntel(srcReg), IntelData.LONG));
			
		} else {
			
			op = setAddressing(dstAddrType, IntelRegisters.getActualReg(SicRegisters.toIntel(dstReg), IntelData.LONG)) + ", ";
			     // setAddressing(srcAddrType, src);
			
			if(indexed) { // indexed addressing
				
				op += "[" + src + " + " + IntelRegisters.getActualReg(SicRegisters.toIntel(SicRegisters.REG_X), IntelData.LONG) + "]";
				
			} else { // normal addresing
				
				op += setAddressing(srcAddrType, src);
				
			}
			
		}
		
		return super.toString(l, i, op, srcInstr);
		// return l + i + op;
	}

}
