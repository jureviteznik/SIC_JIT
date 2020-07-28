package sic.sim.addons.jit.sic2intel.structure.sic;


public class SicInstrTIO extends SicInstrF1 {

	@Override
	public void accept(SicInstrVisitor visitor) {
		visitor.visit(this);
	}
	
	public String toString() {
		return super.toString("TIO");
	}
}
