package sic.sim.addons.jit.sic2intel.structure.sic;

public interface SicSource {

	/**
	 * Accept method used to "accept" a visitor.
	 * 
	 * @param visitor
	 */
	void accept(SicInstrVisitor visitor);

}
