package sic.sim.addons.jit.sic2intel.structure.sic;


public class SicInstrBYTE extends SicInstr {
	
	public String value;
	
	public SicInstrBYTE(String number) {
		this.value = number;
	}
	
	@Override
	public void accept(SicInstrVisitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return super.toString(label, "BYTE", value);
	}
}
