package sic.sim.addons.jit.sic2intel.structure.intel;

import sic.sim.addons.jit.sic2intel.structure.sic.SicInstr;
import sic.sim.addons.jit.sic2intel.structure.sic.SicRegisters;

/**
 * 
 * @author benjamin
 * 
 * The FIST instruction converts the value in the ST(0) register to a signed integer and stores the
 * result in the destination operand. Values can be stored in word- or short-integer format. The
 * destination operand specifies the address where the first byte of the destination value is to be
 * stored.
 * 
 */
public class IntelInstrFIST extends IntelInstr {

	private Integer srcAddrType;
	
	/**
	 * Source -- the first and only operand 
	 * 
	 */
	private String src;
	
	private boolean indexed;
	
	private SicInstr srcInstr;
	
	public IntelInstrFIST(String label, Integer srcAddrType, String src, boolean indexed, SicInstr srcInstr) {
		this.label = label;
		this.srcAddrType = srcAddrType;
		this.src = src;
		this.indexed = indexed;
		this.srcInstr = srcInstr;
	}

	@Override
	public String toString() {
		// label
		String l = (label != null ? label + ":" : "");// + "\t\t";
		
		// instruction
		String i = "fist";// + "\t\t";
		
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
			
			op += "dword ptr [" + src + " + " + IntelRegisters.getActualReg(SicRegisters.toIntel(SicRegisters.REG_X), IntelData.LONG) + "]";
			
		} else { // normal addresing
			
			op += "dword ptr " + setAddressing(srcAddrType, src);
			
		}
		
		return super.toString(l, i, op, srcInstr);
		// return l + i + op;
	}

}