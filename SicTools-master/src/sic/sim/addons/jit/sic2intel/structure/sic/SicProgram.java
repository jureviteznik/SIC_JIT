package sic.sim.addons.jit.sic2intel.structure.sic;

import java.util.LinkedList;

public class SicProgram implements SicSource {

	/**
	 * List of instructions contained in the program.
	 */
	public LinkedList<SicInstr> instrs;

	/**
	 * Main constructor.
	 * 
	 * @param instrs
	 * 		List of SIC instructions
	 */
	public SicProgram(LinkedList<SicInstr> instrs) {
		this.instrs = instrs;
	}

	@Override
	public void accept(SicInstrVisitor visitor) {
		for (SicInstr instr : instrs) {
			instr.accept(visitor);
		}
	}

}
