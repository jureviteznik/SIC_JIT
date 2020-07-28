package sic.sim.addons.jit.sic2intel.structure.sic;

public abstract class SicInstr implements SicSource {

	/**
	 * Instruction label. Every instruction can have a label.
	 */
	public String label;

	public String toString(String instr, String identifier) {
		String l = label != null ? label : "";

		return toString(l, instr, identifier);
	}
	
	public String toString(String l, String i, String op) {
		return String.format("%1$-16s%2$-8s%3$s\n", "#" + l, i, op);
	}	
}
