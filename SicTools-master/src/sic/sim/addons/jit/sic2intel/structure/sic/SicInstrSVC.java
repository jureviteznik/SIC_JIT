package sic.sim.addons.jit.sic2intel.structure.sic;


public class SicInstrSVC extends SicInstrF2 {
	
	public String number;
	
	public SicInstrSVC(String number) {
		this.number = number;
	}
	
	@Override
	public void accept(SicInstrVisitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return super.toString("SVC", number);
	}
}
