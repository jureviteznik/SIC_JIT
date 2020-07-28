package sic.sim.addons.jit.sic2intel.structure.intel;

import sic.sim.addons.jit.sic2intel.structure.CodeGenerator;
import sic.sim.addons.jit.sic2intel.structure.sic.SicData;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstr;
import sic.sim.addons.jit.sic2intel.structure.sic.SicRegisters;

/**
 * 
 * @author benjamin
 *
 * This instruction performs a bitwise AND operation on the destination (first) and source (second)
 * operands and stores the result in the destination operand location. The source operand can be an
 * immediate, a register, or a memory location; the destination operand can be a register or a
 * memory location. Two memory operands cannot, however, be used in one instruction. Each bit
 * of the instruction result is a 1 if both corresponding bits of the operands are 1; otherwise, it
 * becomes a 0.
 *
 */
public class IntelInstrAND extends IntelInstr {

	private Integer dstAddrType;
	
	/** 
	 * Destination -- the first operand. 
	 * 
	 * The destination is always a register.
	 * 
	 */
	private Integer dstReg;
	
	private Integer srcAddrType;
	
	/** 
	 * Source -- the second operand.
	 * 
	 * A memory location or an immediate operand when using simple or immediate addressing.
	 * 
	 */
	private String src;
	
	/**
	 * Source -- the second operand.
	 *  
	 * A register when using indirect addressing.
	 *  
	 */
	private Integer srcReg;
	
	private boolean indexed;
	
	private SicInstr srcInstr;
	
	public IntelInstrAND(String label, Integer dstAddrType, Integer dstReg, Integer srcAddrType, String src, boolean indexed, SicInstr srcInstr) {
		this.label = label;
		this.dstAddrType = dstAddrType;
		this.dstReg = dstReg;
		this.srcAddrType = srcAddrType;
		this.src = src;
		this.indexed = indexed;
		this.srcInstr = srcInstr;
	}

	public IntelInstrAND(String label, Integer dstAddrType, Integer dstReg, Integer srcAddrType, Integer srcReg, boolean indexed, SicInstr srcInstr) {
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
		String i = "and";// + "\t\t";
		
		// operands
		String op = "";
		
		Integer type;
		
		// dst register depends on the size of src operand
		if (srcReg != null) { // src operand is a register
			
			op = setAddressing(dstAddrType, IntelRegisters.getActualReg(SicRegisters.toIntel(dstReg), IntelData.LONG)) + 
			     ", " + 
			     setAddressing(srcAddrType, IntelRegisters.getActualReg(SicRegisters.toIntel(srcReg), IntelData.LONG));
			
		} else if (src != null) { 
			
			// src operand is a memory location
			//if ((type = DataInspector.dataTypes.get(src)) != null) {
				
			type = CodeGenerator.dataTypes.get(src);
			if (type == null || type == SicData.WORD || type == SicData.RESW) {

				op = setAddressing(dstAddrType, IntelRegisters.getActualReg(SicRegisters.toIntel(dstReg), IntelData.LONG)) + ", "; 
					 //setAddressing(srcAddrType, src);
				
			} else if (type == SicData.BYTE || type == SicData.RESB) {
					
					op = setAddressing(dstAddrType, IntelRegisters.getActualReg(SicRegisters.toIntel(dstReg), IntelData.LONG/*IntelData.BYTE*/)) + ", "; 
						 //setAddressing(srcAddrType, src);					
			} 
				
			if (indexed) { // indexed addressing
				
				op += "[" + src + " + " + IntelRegisters.getActualReg(SicRegisters.toIntel(SicRegisters.REG_X), IntelData.LONG) + "]";
				
			} else { // normal addresing
				
				op += setAddressing(srcAddrType, src);
				
			}
		}
		
		return super.toString(l, i, op, srcInstr);
		//return l + i + op;
	}
}