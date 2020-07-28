package sic.sim.addons.jit.sic2intel.structure.intel;

import sic.sim.addons.jit.sic2intel.structure.sic.SicInstr;

/**
 * 
 * @author benjamin
 * 
 * This instruction checks the state of one or more of the status flags in the EFLAGS register (CF,
 * OF, PF, SF, and ZF) and, if the flags are in the specified state (condition), performs a jump to the
 * target instruction specified by the destination operand. A condition code (cc) is associated with
 * each instruction to indicate the condition being tested for. If the condition is not satisfied, the
 * jump is not performed and execution continues with the instruction following the Jcc instruc-
 * tion.
 *
 */
public class IntelInstrJcc extends IntelInstr {

	public static final int J = 1;   // JMP
	public static final int JEQ = 2; // JE
	public static final int JGT = 3; // JG
	public static final int JLT = 4; // JL
	
	/** storage type */
	private Integer type;
	
	private String loc;
	
	private SicInstr srcInstr;

	public IntelInstrJcc(String label, Integer type, String loc, SicInstr srcInstr) {
		this.label = label;
		this.type = type;
		this.loc = loc;
		this.srcInstr = srcInstr;
	}

	@Override
	public String toString() {
		
		// label
		String l = (label != null ? label + ":" : "");// + "\t\t";
		
		// instruction
		String i = "";
		
		switch (this.type) {
		case IntelInstrJcc.J:
			i = "jmp";
			break;
		case IntelInstrJcc.JEQ:
			i = "je";
			break;
		case IntelInstrJcc.JGT:
			i = "jg";
			break;
		case IntelInstrJcc.JLT:
			i = "jl";
			break;
		}
		
		// operands
		String op = loc;

		return super.toString(l, i, op, srcInstr);
		// return l + i + op;
	}

}
