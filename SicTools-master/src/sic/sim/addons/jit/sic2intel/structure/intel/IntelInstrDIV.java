package sic.sim.addons.jit.sic2intel.structure.intel;

import sic.sim.addons.jit.sic2intel.structure.CodeGenerator;
import sic.sim.addons.jit.sic2intel.structure.sic.SicAddressing;
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
public class IntelInstrDIV extends IntelInstr {

	private Integer srcAddrType;
	
	/** 
	 * Source -- the first operand 
	 * 
	 * Can be a memory location or a register
	 * 
	 */
	private String src;
	private Integer srcReg;
	
	private boolean indexed;
	
	private SicInstr srcInstr;

	public IntelInstrDIV(String label, Integer srcAddrType, String src, boolean indexed, SicInstr srcInstr) {
		this.label = label;
		this.srcAddrType = srcAddrType;
		this.src = src;
		this.indexed = indexed;
		this.srcInstr = srcInstr;
	}
	
	public IntelInstrDIV(String label, Integer srcAddrType, Integer srcReg, boolean indexed, SicInstr srcInstr) {
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
		String i = "idiv";// + "\t\t";
			
		// operands
		String op = "";
		
		if(src != null) {
			Integer type = CodeGenerator.dataTypes.get(src);
			if (type == null || type == SicData.WORD || type == SicData.RESW) {
				op = "dword ptr "; 
				if (indexed) {
					op += "[" + src + " + " + IntelRegisters.getActualReg(SicRegisters.toIntel(SicRegisters.REG_X), IntelData.LONG) + "]";
				} else {
					op += setAddressing(srcAddrType, src);
				}
			} else if (type == SicData.BYTE || type == SicData.RESB) {
				op = "byte ptr ";
				if (indexed) {
					op += "[" + src + " + " + IntelRegisters.getActualReg(SicRegisters.toIntel(SicRegisters.REG_X), IntelData.LONG) + "]";
				} else {
					op += setAddressing(srcAddrType, src);
				}
			}
		} else {
			if (srcAddrType == SicAddressing.SIMPLE) {
				op = "dword ptr " + setAddressing(srcAddrType, IntelRegisters.getActualReg(SicRegisters.toIntel(srcReg), IntelData.LONG));
			} else {
				op = IntelRegisters.getActualReg(SicRegisters.toIntel(srcReg), IntelData.LONG);
			}
		}
		
		return super.toString(l, i, op, srcInstr);
		// return l + i + op;
	}

}
