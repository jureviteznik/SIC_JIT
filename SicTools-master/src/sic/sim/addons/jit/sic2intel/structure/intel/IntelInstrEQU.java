package sic.sim.addons.jit.sic2intel.structure.intel;

import sic.sim.addons.jit.sic2intel.structure.sic.SicInstr;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrEQU_BinExpr;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrEQU_Expr;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrEQU_UnExpr;

public class IntelInstrEQU extends IntelInstr {
	
	private SicInstrEQU_Expr expr;
	
	private SicInstr srcInstr;

	/** Counter of auto generated labels */
	private static int counter;
	
	/** User defined label */
	public IntelInstrEQU(String label, SicInstrEQU_Expr expr, SicInstr srcInstr) {
		this.label = label;
		this.expr = expr;
		this.srcInstr = srcInstr;
	}
	
	/** Auto-generated label */
	public IntelInstrEQU(SicInstrEQU_Expr expr) {
		this.label = "_e" + IntelInstrEQU.counter;
		this.expr = expr;
		
		IntelInstrEQU.counter++;
	}

	@Override
	public String toString() {
		return super.toString("", ".equ", this.label + ", " + produceString(expr), srcInstr);
	}
	
	public String produceString(SicInstrEQU_Expr expr) {
		if(expr instanceof SicInstrEQU_UnExpr) {
			return produceString((SicInstrEQU_UnExpr)expr);
		} else {
			return produceString((SicInstrEQU_BinExpr)expr);
		}
	}
	
	public String produceString(SicInstrEQU_UnExpr expr) {
		switch(expr.expr) {
		case SicInstrEQU_UnExpr.STAR:
			return "$";
		case SicInstrEQU_UnExpr.NUM:
		case SicInstrEQU_UnExpr.ID:
			return expr.numOrId;
		default:
			return "";
		}
	}
	
	public String produceString(SicInstrEQU_BinExpr expr) {
		switch(expr.op) {
		case SicInstrEQU_BinExpr.PLUS:
			return produceString(expr.expr1) + "+" + produceString(expr.expr2);
		case SicInstrEQU_BinExpr.MINUS:
			return produceString(expr.expr1) + "-" + produceString(expr.expr2);
		case SicInstrEQU_BinExpr.DIVIDE:
			return produceString(expr.expr1) + "/" + produceString(expr.expr2);
		default:
			return "";
		}
	}

}
