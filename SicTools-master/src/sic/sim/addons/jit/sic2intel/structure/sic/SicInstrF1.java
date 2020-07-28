package sic.sim.addons.jit.sic2intel.structure.sic;

public abstract class SicInstrF1 extends SicInstr {
	
	public String toString(String instr) {
		String l = label != null ? label : "";
		
		//return l + "\t" + instr;
		return super.toString(l, instr, "");
	}
}