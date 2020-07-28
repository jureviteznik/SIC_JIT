package sic.sim.addons.jit.sic2intel.structure.intel;

import sic.sim.addons.jit.sic2intel.structure.CodeGenerator;
import sic.sim.addons.jit.sic2intel.structure.sic.SicData;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstr;
import sic.sim.addons.jit.sic2intel.structure.sic.SicRegisters;

/**
 * 
 * @author benjamin
 * 
 * This instruction subtracts the second operand (source operand) from the first operand (destina-
 * tion operand) and stores the result in the destination operand. The destination operand can be a
 * register or a memory location; the source operand can be an immediate, register, or memory
 * location. (However, two memory operands cannot be used in one instruction.) When an imme-
 * diate value is used as an operand, it is sign-extended to the length of the destination operand
 * format.
 * 
 * The SUB instruction does not distinguish between signed or unsigned operands. Instead, the
 * processor evaluates the result for both data types and sets the OF and CF flags to indicate a
 * borrow in the signed or unsigned result, respectively. The SF flag indicates the sign of the signed
 * result.
 * 
 */
public class IntelInstrSUB extends IntelInstr {

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
	 * A memory location or an immediate operand when using an ADD or ADDF instruction.
	 * 
	 */
	private String src;
	
	/**
	 *  Source -- the second operand.
	 *  
	 *  A register when using an SUBR instruction.
	 *  
	 */
	private Integer srcReg;
	
	private boolean indexed;
	
	private SicInstr srcInstr;
	
	/** Subtracts the value of src from the value of dst register */
	public IntelInstrSUB(String label, Integer dstAddrType, Integer dstReg, Integer srcAddrType, String src, boolean indexed, SicInstr srcInstr) {
		this.label = label;
		this.dstAddrType = dstAddrType;
		this.dstReg = dstReg;
		this.srcAddrType = srcAddrType;
		this.src = src;
		this.indexed = indexed;
		this.srcInstr = srcInstr;
	}
	
	/** Subtracts the value of the src register from the value of dst register */
	public IntelInstrSUB(String label, Integer dstAddrType, Integer dstReg, Integer srcAddrType, Integer srcReg, boolean indexed, SicInstr srcInstr) {
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
		String i = "sub";// + "\t\t";
		
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
				
			}
			else if (type == SicData.BYTE || type == SicData.RESB) {
					
					op = setAddressing(dstAddrType, IntelRegisters.getActualReg(SicRegisters.toIntel(dstReg), IntelData.LONG/*IntelData.BYTE*/)) + ", "; 
						 //setAddressing(srcAddrType, src);					
					
				} 
				
				if(indexed) { // indexed addressing
					
					op += "[" + src + " + " + IntelRegisters.getActualReg(SicRegisters.toIntel(SicRegisters.REG_X), IntelData.LONG) + "]";
					
				} else { // normal addresing
					
					op += setAddressing(srcAddrType, src);
					
				}
			
			// src operand is a number
			/*
			} else { 
				
			}
			*/
		}
		
		return super.toString(l, i, op, srcInstr);
		// return l + i + op;
	}

}