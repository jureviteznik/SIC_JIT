package sic.sim.addons.jit.sic2intel.structure.sic;


public class SicInstrEQU extends SicInstr {
	
	public SicInstrEQU_Expr expr;
	
	public SicInstrEQU(SicInstrEQU_Expr expr) {
		this.expr = expr;
	}
	
	@Override
	public void accept(SicInstrVisitor visitor) {
		visitor.visit(this);
	}
	
	public String toString() {
		return super.toString(label, "EQU", expr.toString());
	}
}
