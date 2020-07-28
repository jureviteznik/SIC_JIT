package sic.sim.addons.jit.sic2intel.structure.sic;


public class SicInstrRMO extends SicInstrF2 {

	public Integer register1;
	public Integer register2;
	
	public SicInstrRMO(Integer register1, Integer register2) {
		this.register1 = register1;
		this.register2 = register2;
	}
	
	@Override
	public void accept(SicInstrVisitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return super.toString("RMO", register1, register2);
	}
}
