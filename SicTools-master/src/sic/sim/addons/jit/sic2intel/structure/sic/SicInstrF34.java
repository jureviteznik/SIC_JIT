package sic.sim.addons.jit.sic2intel.structure.sic;

public abstract class SicInstrF34 extends SicInstr {

	public boolean extended;

	public boolean indexed;

	public String toString(String instr) {
		String l = label != null ? label : "";
		
		return l + "\t" + instr;
	}
	
	public String toString(String instr, Integer addressing, String identifier) {
		String l = label != null ? label : "";
		String e = extended ? "+" : "";
		String i = indexed ? ", X" : "";
		
		switch (addressing) {
		case SicAddressing.IMMEDIATE:
			//return l + "\t" + e + instr + "\t" + "#" + identifier + i;
			return super.toString(l, e + instr, "#" + identifier + i);
		case SicAddressing.INDIRECT:
			//return l + "\t" + e + instr + "\t" + "@" + identifier + i;
			return super.toString(l, e + instr, "@" + identifier + i);
		default:
			//return l + "\t" + e + instr + "\t" + "" + identifier + i;
			return super.toString(l, e + instr, identifier + i);
		}
	}
}
