package sic.sim.addons.jit.sic2intel.structure.intel;

import sic.sim.addons.jit.sic2intel.structure.CodeGenerator;
import sic.sim.addons.jit.sic2intel.structure.sic.SicData;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstr;
import sic.sim.addons.jit.sic2intel.structure.sic.SicRegisters;

/**
 * 
 * @author benjamin
 *
 * This instruction divides (unsigned) the value in the AX register, DX:AX register pair, or
 * EDX:EAX register pair (dividend) by the source operand (divisor) and stores the result in the
 * AX (AH:AL), DX:AX, or EDX:EAX registers. The source operand can be a general-purpose
 * register or a memory location.
 *
 */
public class IntelInstrIMUL extends IntelInstr {

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
	 * A memory location or an immediate operand when using the MUL instruction.
	 * 
	 */
	private String src;
	
	/**
	 * Source -- the second operand.
	 *  
	 * A register when using the MULR instruction.
	 *  
	 */
	private Integer srcReg;
	
	private boolean indexed;
	
	private SicInstr srcInstr;

	public IntelInstrIMUL(String label, Integer dstAddrType, Integer dstReg, Integer srcAddrType, String src, boolean indexed, SicInstr srcInstr) {
		this.label = label;
		this.dstAddrType = dstAddrType;
		this.dstReg = dstReg;
		this.srcAddrType = srcAddrType;
		this.src = src;
		this.indexed = indexed;
		this.srcInstr = srcInstr;
	}
	
	public IntelInstrIMUL(String label, Integer dstAddrType, Integer dstReg, Integer srcAddrType, Integer srcReg, boolean indexed, SicInstr srcInstr) {
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
		String i = "imul";// + "\t\t";
			
		// operands
		String op = "";
		
		if(src != null) {
			Integer type = CodeGenerator.dataTypes.get(src);
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
		} else {
			//if (srcAddrType == SicAddressing.SIMPLE) {
			op = IntelRegisters.getActualReg(SicRegisters.toIntel(dstReg), IntelData.LONG) + ", " + 
				 setAddressing(srcAddrType, IntelRegisters.getActualReg(SicRegisters.toIntel(srcReg), IntelData.LONG));
			//} else {
			//	op = IntelRegisters.getActualReg(SicRegisters.toIntel(srcReg), IntelData.LONG);
			//}
		}
		
		return super.toString(l, i, op, srcInstr);
		// return l + i + op;
	}

}
