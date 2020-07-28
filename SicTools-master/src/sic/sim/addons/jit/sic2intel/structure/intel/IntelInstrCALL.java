package sic.sim.addons.jit.sic2intel.structure.intel;

import sic.sim.addons.jit.sic2intel.structure.sic.SicInstr;

/**
 * 
 * @author benjamin
 *
 * This instruction saves procedure linking information on the stack and branches to the procedure
 * (called procedure) specified with the destination (target) operand. The target operand specifies
 * the address of the first instruction in the called procedure. This operand can be an immediate
 * value, a general-purpose register, or a memory location.
 *
 */
public class IntelInstrCALL extends IntelInstr {

	/** 
	 * 
	 * Source -- the first and only operand
	 * 
	 *  The label (location) of the procedure to call (jump to). 
	 *
	 */
	private String loc;
	
	private SicInstr srcInstr;
	
	public IntelInstrCALL(String label, String loc, SicInstr srcInstr) {
		this.label = label;
		this.loc = loc;
		this.srcInstr = srcInstr;
	}

	@Override
	public String toString() {
		// label
		String l = (label != null ? label + ":" : "");// + "\t\t";
		
		// instruction
		String i = "call";// + "\t\t";
		
		// operands
		String op = loc;
		
		return super.toString(l, i, op, srcInstr);
		// return l + i + op;
	}

}