package sic.sim.addons.jit.sic2intel.structure.intel;

import sic.sim.addons.jit.sic2intel.structure.sic.SicInstr;

/**
 * 
 * @author benjamin
 *
 * This instruction transfers program control to a return address located on the top of the stack. The
 * address is usually placed on the stack by a CALL instruction, and the return is made to the
 * instruction that follows the CALL instruction.
 * 
 * The optional source operand specifies the number of stack bytes to be released after the return
 * address is popped; the default is none. This operand can be used to release parameters from the
 * stack that were passed to the called procedure and are no longer needed.
 *
 */
public class IntelInstrRET extends IntelInstr {

	/** optional operand */
	private String loc;
	
	private SicInstr srcInstr;
	
	/**  */
	public IntelInstrRET(String label, String loc, SicInstr srcInstr) {
		this.label = label;
		this.loc = loc;
		this.srcInstr = srcInstr;
	}

	@Override
	public String toString() {
		// label
		String l = (label != null ? label + ":" : "");// + "\t\t";
				
		// instruction
		String i = "ret";// + "\t\t";
			
		// operands
		String op = this.loc != null ? this.loc : "";
		
		return super.toString(l, i, op, srcInstr);
		// return (label != null ? label + ":" : "") + "\t\t" + "ret" + (op != null ? "\t\t" + op : "");
	}

}