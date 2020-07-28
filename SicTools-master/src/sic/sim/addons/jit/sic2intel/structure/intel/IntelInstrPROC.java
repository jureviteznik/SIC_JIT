package sic.sim.addons.jit.sic2intel.structure.intel;

import java.util.List;

public abstract class IntelInstrPROC extends IntelInstr {

	/** Counter of auto generated labels */
	private static int counter = 0;
	
	public List<IntelInstr> instrs;
	
	public abstract void genInstrs();
	
	public static String genLabel() {
		IntelInstrPROC.counter++;
		return "_p" + (IntelInstrPROC.counter - 1);
	}
	
	public String listToString() {
		String result = "";
		
		for (IntelInstr i : instrs) {
			result += i.toString();
		}
		
		return result;
	}
}
