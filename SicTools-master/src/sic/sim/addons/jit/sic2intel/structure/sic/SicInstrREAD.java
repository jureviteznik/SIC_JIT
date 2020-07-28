package sic.sim.addons.jit.sic2intel.structure.sic;


public class SicInstrREAD extends SicInstr {

	/** Length of the input message */
	public String length;
	
	/** The variable fro storing the message */
	public String identifier;

	public SicInstrREAD(String var, String len) {
		this.identifier = var;
		this.length = len;
	}

	@Override
	public void accept(SicInstrVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return super.toString("", ".<READ(" + identifier + ", " + length + ")>", "");
	}
}
