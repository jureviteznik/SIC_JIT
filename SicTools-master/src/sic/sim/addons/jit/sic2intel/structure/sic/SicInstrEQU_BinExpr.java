package sic.sim.addons.jit.sic2intel.structure.sic;

public class SicInstrEQU_BinExpr extends SicInstrEQU_Expr {
	
	public static final int PLUS = 0;
	public static final int MINUS = 1;
	public static final int DIVIDE = 2;
	
	public int op;
	public SicInstrEQU_Expr expr1;
	public SicInstrEQU_Expr expr2;
	
	public SicInstrEQU_BinExpr(int op, SicInstrEQU_Expr expr1, SicInstrEQU_Expr expr2) {
		this.op = op;
		this.expr1 = expr1;
		this.expr2 = expr2;
	}
	
	@Override
	public String toString() {
		return expr1.toString() + "+-/".charAt(op) + expr2.toString();
	}
	
	/*
	@Override
	public void accept(SicVisitor visitor) {
		visitor.visit(this);
	}
	*/
}
