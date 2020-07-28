package sic.sim.addons.jit.sic2intel.structure.sic;


public class SicInstrSTART extends SicInstr {

	public Object identifier;
	
	public String number;
	
	public SicInstrSTART(Object identifier, String number) {
		this.identifier = identifier;
		this.number = number;
	}
	
	@Override
	public void accept(SicInstrVisitor visitor) {
		visitor.visit(this);
	}

}
