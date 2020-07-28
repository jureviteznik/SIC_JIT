package sic.sim.addons.jit.sic2intel.structure.sic;


public class SicInstrSHIFTL extends SicInstrF2 {
	
	public Integer register;
	
	public String number;
	
	public SicInstrSHIFTL(Integer register, String number) {
		this.register = register;
		this.number = number;
	}
	
	@Override
	public void accept(SicInstrVisitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return super.toString("SHIFTL", register, number);
	}
}
