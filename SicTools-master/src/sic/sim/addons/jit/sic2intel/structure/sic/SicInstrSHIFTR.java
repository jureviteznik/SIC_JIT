package sic.sim.addons.jit.sic2intel.structure.sic;


public class SicInstrSHIFTR extends SicInstrF2 {

	public Integer register;
	
	public String number;
	
	public SicInstrSHIFTR(Integer register, String number) {
		this.register = register;
		this.number = number;
	}
	
	@Override
	public void accept(SicInstrVisitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return super.toString("SHIFTR", register, number);
	}
}
