package sic.sim.addons.jit.sic2intel.structure.intel;

import sic.sim.addons.jit.sic2intel.structure.sic.SicInstr;
import sic.sim.addons.jit.sic2intel.structure.sic.SicRegisters;

/**
 * 
 * @author benjamin
 * 
 * The FST instruction copies the value in the ST(0) register to the destination operand, which can
 * be a memory location or another register in the FPU register stack. When storing the value in
 * memory, the value is converted to single- or double-real format.
 * 
 */
public class IntelInstrFST extends IntelInstr {

	private Integer dstAddrType;
	
	/**
	 * Source -- the first and only operand 
	 * 
	 */
	private String dst;
	private Integer dstReg;
	
	private boolean indexed;
	
	private SicInstr srcInstr;
	
	public IntelInstrFST(String label, Integer dstAddrType, String dst, boolean indexed, SicInstr srcInstr) {
		this.label = label;
		this.dstAddrType = dstAddrType;
		this.dst = dst;
		this.indexed = indexed;
		this.srcInstr = srcInstr;
	}
	
	public IntelInstrFST(String label, Integer dstAddrType, Integer dstReg, boolean indexed, SicInstr srcInstr) {
		this.label = label;
		this.dstAddrType = dstAddrType;
		this.dstReg = dstReg;
		this.indexed = indexed;
		this.srcInstr = srcInstr;
	}

	@Override
	public String toString() {
		// label
		String l = (label != null ? label + ":" : "");// + "\t\t";
		
		// instruction
		String i = "fst";// + "\t\t";
		
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
			
			op += "qword ptr [" + dst + " + " + IntelRegisters.getActualReg(SicRegisters.toIntel(SicRegisters.REG_X), IntelData.LONG) + "]";
			
		} else { // normal addresing
			
			if (dst != null) {
				op += "qword ptr " + setAddressing(dstAddrType, dst);
			} else {
				op += "qword ptr " + setAddressing(dstAddrType, IntelRegisters.getActualReg(SicRegisters.toIntel(dstReg), IntelData.LONG));	
			}
		}
		
		return super.toString(l, i, op, srcInstr);
		// return l + i + op;
	}

}