package sic.sim.addons.jit.sic2intel.structure.sic;


public class SicInstrEND extends SicInstr {

	public String identifier; 
	
	public SicInstrEND(String identifier) {
		this.identifier = identifier;
	}
	
	@Override
	public void accept(SicInstrVisitor visitor) {
		visitor.visit(this);
	}
	
	public String toString() {
		return super.toString("END", identifier);
	}
}
