package sic.sim.addons.jit.sic2intel.structure.sic;


public class SicInstrRSUB extends SicInstrF34 {
	
	public SicInstrRSUB(boolean extended) {
		this.extended = extended;
	}
	
	@Override
	public void accept(SicInstrVisitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return super.toString("RSUB");
	}
}
