package sic.sim.addons.jit.sic2intel.structure.sic;

public abstract class SicInstrF2 extends SicInstr {
	
	public String toString(String instr, Integer reg) {
		String l = label != null ? label : "";

	  	//return l + "\t" + instr + "\t" + SicRegisters.regToString(reg);
		return super.toString(l, instr, SicRegisters.regToString(reg));
	}
	
	public String toString(String instr, String num) {
		String l = label != null ? label : "";

	  	//return l + "\t" + instr + "\t" + num;
		return super.toString(l, instr, num);
	}
	
	public String toString(String instr, Integer reg1, Integer reg2) {
		String l = label != null ? label : "";
		
		//return l + "\t" + instr + "\t" + SicRegisters.regToString(reg1) + ", " + SicRegisters.regToString(reg2);
		return super.toString(l, instr, SicRegisters.regToString(reg1) + ", " + SicRegisters.regToString(reg2));
	}
	
	public String toString(String instr, Integer reg, String num) {
		String l = label != null ? label : "";
		
		//return l + "\t" + instr + "\t" + SicRegisters.regToString(reg) + ", " + num;
		return super.toString(l, instr, SicRegisters.regToString(reg) + ", " + num);
	}
}