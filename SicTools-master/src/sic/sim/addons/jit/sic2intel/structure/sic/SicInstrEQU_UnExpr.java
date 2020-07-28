package sic.sim.addons.jit.sic2intel.structure.sic;

public class SicInstrEQU_UnExpr extends SicInstrEQU_Expr {
	
	public static final int STAR = 0;
	public static final int NUM = 1;
	public static final int ID = 2;
	
	public int expr;
	public String numOrId;
	
	public SicInstrEQU_UnExpr(int expr) {
		this.expr = expr;
	}
	
	public SicInstrEQU_UnExpr(int expr, String numOrId) {
		this.expr = expr;
		this.numOrId = numOrId;
	}
	
	@Override
	public String toString() {
		if (expr == SicInstrEQU_UnExpr.STAR) {
			return "*";
		} else {
			return numOrId;
		}
	}
	
	/*
	@Override
	public void accept(SicVisitor visitor) {
		visitor.visit(this);
	}
	*/
}
