package sic.sim.addons.jit.sic2intel.structure.sic;


public class SicInstrWRITE extends SicInstr {

	public String identifier;
	
	public String length;

	public SicInstrWRITE(String identifier) {
		this.identifier = identifier;
	}

	public SicInstrWRITE(String identifier, String length) {
		this.identifier = identifier;
		this.length = length;
	}
	
	@Override
	public void accept(SicInstrVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return super.toString("", ".<WRITE('" + identifier + (length != null ? ", " + length : "") + "')>", "");
	}
}
