package sic.sim.addons.jit.sic2intel.structure.intel;

import sic.sim.addons.jit.sic2intel.structure.sic.SicInstr;
import sic.sim.addons.jit.sic2intel.structure.sic.SicRegisters;

/**
 * 
 * @author benjamin
 *
 * This instruction copies the second operand (source operand) to the first operand (destination
 * operand) and zero extends the value to 16 or 32 bits. The source operand can be a general-purpose
 * register or a segment register; the destination register can be a general-purpose register or
 * segment register.
 *
 */
public class IntelInstrMOVZX extends IntelInstr {
	
	private Integer dstAddrType;
	
	/** 
	 * Destination -- the first operand.
	 * 
	 * Can only be register.
	 * 
	 */
	private Integer dstReg;
	
	private Integer srcAddrType;
	
	/** 
	 * Source -- the second operand.
	 * 
	 * Can be a memory location or a register.
	 *  
	 */
	private String src;
	private Integer srcReg;
	
	private boolean indexed;
	
	private boolean loadByte = false;
	
	private SicInstr srcInstr;
	
	/**
	 * Constructor for creating a MOVZX instruction, that moves a byte from a 
	 * register source to a register destination.
	 */
	public IntelInstrMOVZX(String label, Integer dstAddrType, Integer dstReg, Integer srcAddrType, Integer srcReg, boolean loadByte, boolean indexed, SicInstr srcInstr) {
		this.label = label;
		this.dstAddrType = dstAddrType;
		this.dstReg = dstReg;
		this.srcAddrType = srcAddrType;
		this.srcReg = srcReg;
		this.loadByte = true;
		this.indexed = indexed;
		this.srcInstr = srcInstr;
	}

	@Override
	public String toString() {
		
		// label
		String l = (label != null ? label + ":" : "");// + "\t\t";
				
		// instruction
		String i = "movzx";// + "\t\t";
			
		// operands
		String op = "";
		
		if(dstReg != null && srcReg != null) {
			
			if (srcReg == SicRegisters.REG_A && loadByte) {
				op = setAddressing(dstAddrType, IntelRegisters.getActualReg(SicRegisters.toIntel(dstReg), IntelData.LONG)) +
					 ", " +
					 setAddressing(srcAddrType, IntelRegisters.getActualReg(SicRegisters.toIntel(srcReg), IntelData.BYTE));
			}
		}
		
		return super.toString(l, i, op, srcInstr);
		// return l + i + op;
	}

}