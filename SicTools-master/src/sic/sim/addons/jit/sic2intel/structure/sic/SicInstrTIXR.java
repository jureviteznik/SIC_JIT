package sic.sim.addons.jit.sic2intel.structure.sic;


public class SicInstrTIXR extends SicInstrF2 {

	public Integer register;
	
	public SicInstrTIXR(Integer register) {
		this.register = register;
	}
	
	@Override
	public void accept(SicInstrVisitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return super.toString("TIXR", register);
	}
}
