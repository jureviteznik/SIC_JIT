package sic.sim.addons.jit.sic2intel.structure.intel;

import sic.sim.addons.jit.sic2intel.structure.sic.SicInstr;
import sic.sim.addons.jit.sic2intel.structure.sic.SicRegisters;

/**
 * 
 * @author benjamin
 * 
 * These instructions divide the destination operand by the source operand and stores the result in
 * the destination location. The destination operand (dividend) is always in an FPU register; the
 * source operand (divisor) can be a register or a memory location. Source operands in memory
 * can be in single-real, double-real, word-integer, or short-integer formats.
 * 
 */
public class IntelInstrFDIV extends IntelInstr {

	private Integer srcAddrType;
	
	/**
	 * Source -- the first and only operand 
	 * 
	 */
	private String src;
	private Integer srcReg;
	
	private boolean indexed;
	
	private SicInstr srcInstr;
	
	public IntelInstrFDIV(String label, Integer srcAddrType, String src, boolean indexed, SicInstr srcInstr) {
		this.label = label;
		this.srcAddrType = srcAddrType;
		this.src = src;
		this.indexed = indexed;
		this.srcInstr = srcInstr;
	}
	
	public IntelInstrFDIV(String label, Integer srcAddrType, Integer srcReg, boolean indexed, SicInstr srcInstr) {
		this.label = label;
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
		String i = "fdiv";// + "\t\t";
		
		// operands
		String op = "";
		/*
		Integer type;
		
		type = DataInspector.dataTypes.get(src);
		if (type == null || type == SicData.WORD || type == SicData.RESW) {

			op = IntelRegisters.getActualReg(SicRegisters.toIntel(dstReg), IntelData.LONG) + ", "; 
				 //setAddressing(srcAddrType, src);
			
		}
		else if (type == SicData.BYTE || type == SicData.RESB) {
				
				op = IntelRegisters.getActualReg(SicRegisters.toIntel(dstReg), IntelData.BYTE) + ", "; 
					 //setAddressing(srcAddrType, src);					
				
		} 
		*/	
			
		if(indexed) { // indexed addressing
			
			op += "qword ptr [" + src + " + " + IntelRegisters.getActualReg(SicRegisters.toIntel(SicRegisters.REG_X), IntelData.LONG) + "]";
			
		} else { // normal addresing
			
			if (src != null) {
				op += "qword ptr " + setAddressing(srcAddrType, src);
			} else {
				op += "qword ptr " + setAddressing(srcAddrType, IntelRegisters.getActualReg(SicRegisters.toIntel(srcReg), IntelData.LONG));
			}
		}
		
		return super.toString(l, i, op, srcInstr);
		// return l + i + op;
	}

}