package sic.sim.addons.jit.sic2intel.structure.intel;

import sic.sim.addons.jit.sic2intel.structure.sic.SicInstr;

/**
 * 
 * @author benjamin
 *
 * The INT n instruction generates a call to the interrupt or exception handler specified with the
 * destination operand.
 * 
 */
public class IntelInstrINT extends IntelInstr {

	/** interrupt number */
	private String int_num;
	
	private SicInstr srcInstr;
	
	public IntelInstrINT(String label, String int_num, SicInstr srcInstr) {
		this.label = label;
		this.int_num = int_num;
		this.srcInstr = srcInstr;
	}

	@Override
	public String toString() {
		// label
		String l = (label != null ? label + ":" : "");// + "\t\t";
		
		// instruction
		String i = "int";// + "\t\t";
		
		// operands
		String op = int_num;
		
		return super.toString(l, i, op, srcInstr);
		// return (label != null ? this.label + ":" : "") + "\t\t" + "int" + "\t\t" + int_num;
	}

}