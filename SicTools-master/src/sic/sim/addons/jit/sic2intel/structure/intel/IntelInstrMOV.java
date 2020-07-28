package sic.sim.addons.jit.sic2intel.structure.intel;

import sic.sim.addons.jit.sic2intel.structure.CodeGenerator;
import sic.sim.addons.jit.sic2intel.structure.sic.SicData;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstr;
import sic.sim.addons.jit.sic2intel.structure.sic.SicRegisters;

/**
 * 
 * @author benjamin
 *
 * This instruction copies the second operand (source operand) to the first operand (destination
 * operand). The source operand can be an immediate value, general-purpose register, segment
 * register, or memory location; the destination register can be a general-purpose register, segment
 * register, or memory location. Both operands must be the same size, which can be a byte, a word,
 * or a doubleword.
 *
 */
public class IntelInstrMOV extends IntelInstr {
	
	private Integer dstAddrType;
	
	/** 
	 * Destination -- the first operand.
	 * 
	 * Can be a memory location or a register.
	 * 
	 */
	private String dst;
	private Integer dstReg;
	
	private Integer srcAddrType;
	
	/** 
	 * Source -- the second operand.
	 * 
	 * Can be a memory location, immediate operand or a register.
	 *  
	 */
	private String src;
	private Integer srcReg;
	
	private boolean indexed;
	
	private boolean loadByte = false;
	
	private SicInstr srcInstr;
	
	/**
	 * Constructor for creating a MOV instruction, that moves from a non-register
	 * source to a non-register destination.
	 */
	public IntelInstrMOV(String label, Integer dstAddrType, String dst, Integer srcAddrType, String src, boolean indexed, SicInstr srcInstr) {
		this.label = label;
		this.dstAddrType = dstAddrType;
		this.dst = dst;
		this.srcAddrType = srcAddrType;
		this.src = src;
		this.indexed = indexed;
		this.srcInstr = srcInstr;
	}
	
	/**
	 * Constructor for creating a MOV instruction, that moves from a register
	 * source to a register destination.
	 */
	public IntelInstrMOV(String label, Integer dstAddrType, Integer dstReg, Integer srcAddrType, Integer srcReg, boolean indexed, SicInstr srcInstr) {
		this.label = label;
		this.dstAddrType = dstAddrType;
		this.dstReg = dstReg;
		this.srcAddrType = srcAddrType;
		this.srcReg = srcReg;
		this.indexed = indexed;
		this.srcInstr = srcInstr;
	}
	
	/**
	 * Constructor for creating a MOV instruction, that moves from a non-register
	 * source to a register destination.
	 */
	public IntelInstrMOV(String label, Integer dstAddrType, Integer dstReg, Integer srcAddrType, String src, boolean indexed, SicInstr srcInstr) {
		this.label = label;
		this.dstAddrType = dstAddrType;
		this.dstReg = dstReg;
		this.srcAddrType = srcAddrType;
		this.src  = src;
		this.indexed = indexed;
		this.srcInstr = srcInstr;
	}
	
	/**
	 * Constructor for creating a MOV instruction, that moves from a register
	 * source to a non-register destination.
	 */
	public IntelInstrMOV(String label, Integer dstAddrType, String dst, Integer srcAddrType, Integer srcReg, boolean indexed, SicInstr srcInstr) {
		this.label = label;
		this.dstAddrType = dstAddrType;
		this.dst = dst;
		this.srcAddrType = srcAddrType;
		this.srcReg  = srcReg;
		this.indexed = indexed;
		this.srcInstr = srcInstr;
	}
	
	/**
	 * Constructor for creating a MOV instruction, that moves a byte from a 
	 * non-register source to a register destination.
	 * 
	 * LDCH instruction
	 */
	public IntelInstrMOV(String label, Integer dstAddrType, Integer dstReg, Integer srcAddrType, String src, boolean loadByte, boolean indexed, SicInstr srcInstr) {
		this.label = label;
		this.dstAddrType = dstAddrType;
		this.dstReg = dstReg;
		this.srcAddrType = srcAddrType;
		this.src  = src;
		this.loadByte = true;
		this.indexed = indexed;
		this.srcInstr = srcInstr;
	}
	
	/**
	 * Constructor for creating a MOV instruction, that moves a byte from a 
	 * register source to a non-register destination.
	 * 
	 * STCH instruction
	 */
	public IntelInstrMOV(String label, Integer dstAddrType, String dst, Integer srcAddrType, Integer srcReg, boolean loadByte, boolean indexed, SicInstr srcInstr) {
		this.label = label;
		this.dstAddrType = dstAddrType;
		this.dst = dst;
		this.srcAddrType = srcAddrType;
		this.srcReg  = srcReg;
		this.loadByte = true;
		this.indexed = indexed;
		this.srcInstr = srcInstr;
	}
	
	/**
	 * Constructor for creating a MOV instruction, that moves a byte from a 
	 * register source to a register destination.
	 */
	public IntelInstrMOV(String label, Integer dstAddrType, Integer dstReg, Integer srcAddrType, Integer srcReg, boolean loadByte, boolean indexed, SicInstr srcInstr) {
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
		String i = "mov";// + "\t\t";
			
		// operands
		String op = "";
		
		Integer type;
		
		if(dstReg != null && srcReg != null) {
			
			if (dstReg == SicRegisters.REG_A && loadByte) { // indirect addressing LDCH
				op = setAddressing(dstAddrType, IntelRegisters.getActualReg(SicRegisters.toIntel(dstReg), IntelData.BYTE)) +
					 ", " +
					 setAddressing(srcAddrType, IntelRegisters.getActualReg(SicRegisters.toIntel(srcReg), IntelData.LONG));
			} else if (srcReg == SicRegisters.REG_A && loadByte) { // indirect addressing STCH
				op = setAddressing(dstAddrType, IntelRegisters.getActualReg(SicRegisters.toIntel(dstReg), IntelData.LONG)) +
					 ", " +
					 setAddressing(srcAddrType, IntelRegisters.getActualReg(SicRegisters.toIntel(srcReg), IntelData.BYTE));
			} else { // RMO or indirect addressing mode for LDx and STx
				op = setAddressing(dstAddrType, IntelRegisters.getActualReg(SicRegisters.toIntel(dstReg), IntelData.LONG)) +
					 ", " +
					 setAddressing(srcAddrType, IntelRegisters.getActualReg(SicRegisters.toIntel(srcReg), IntelData.LONG));	
			}
		} else if(dstReg != null && src != null) { // LDx (load)
			
			if (loadByte) {
				
				op = setAddressing(dstAddrType, IntelRegisters.getActualReg(SicRegisters.toIntel(dstReg), IntelData.BYTE)) + ", ";
				
			} else {
				type = CodeGenerator.dataTypes.get(src);
				if (type == null || type == SicData.WORD || type == SicData.RESW || type == SicData.STRING) {
					
					op = setAddressing(dstAddrType, IntelRegisters.getActualReg(SicRegisters.toIntel(dstReg), IntelData.LONG)) + ", ";
					
				} else if (type == SicData.BYTE || type == SicData.RESB) {
						
					op = setAddressing(dstAddrType, IntelRegisters.getActualReg(SicRegisters.toIntel(dstReg), IntelData.LONG/*IntelData.BYTE*/)) + ", ";
					
				} 
			}
				
			if(indexed) { // indexed addressing
				
				op += "[" + src + " + " + IntelRegisters.getActualReg(SicRegisters.toIntel(SicRegisters.REG_X), IntelData.LONG) + "]";
				
			} else { // normal addresing
				
				op += setAddressing(srcAddrType, src);
				
			}
			
		} else if(dst != null && srcReg != null ) { // STx (store)
			
			if(indexed) { // indexed addressing
				
				op = "[" + dst + " + " + IntelRegisters.getActualReg(SicRegisters.toIntel(SicRegisters.REG_X), IntelData.LONG) + "]" + ", ";
				
			} else { // normal addresing
				
				op = setAddressing(dstAddrType, dst) + ", ";
				
			}
			
			if (loadByte) {
				
				op += setAddressing(srcAddrType, IntelRegisters.getActualReg(SicRegisters.toIntel(srcReg), IntelData.BYTE));
				
			} else { 
				type = CodeGenerator.dataTypes.get(dst);
				if (type == null || type == SicData.WORD || type == SicData.RESW || type == SicData.STRING) {
	
					op += setAddressing(srcAddrType, IntelRegisters.getActualReg(SicRegisters.toIntel(srcReg), IntelData.LONG)); 
					
				} else if (type == SicData.BYTE || type == SicData.RESB) {
						
					op += setAddressing(srcAddrType, IntelRegisters.getActualReg(SicRegisters.toIntel(srcReg), IntelData.LONG/*IntelData.BYTE*/)); 					
						
				} 		
			}
			
		}
		
		return super.toString(l, i, op, srcInstr);
		// return l + i + op;
	}

}