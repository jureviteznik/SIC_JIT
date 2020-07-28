package sic.sim.addons.jit.sic2intel.structure.sic;


public class SicInstrWORD extends SicInstr {
	
	public String value;
	
	public SicInstrWORD(String number) {
		this.value = number;
	}
	
	@Override
	public void accept(SicInstrVisitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return super.toString(label, "WORD", value);
	}
}
