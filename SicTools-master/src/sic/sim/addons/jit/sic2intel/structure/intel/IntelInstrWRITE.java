package sic.sim.addons.jit.sic2intel.structure.intel;

import java.util.LinkedList;

import sic.sim.addons.jit.sic2intel.structure.sic.SicAddressing;
import sic.sim.addons.jit.sic2intel.structure.sic.SicRegisters;

/**
 * 
 * @author benjamin
 * 
 */
public class IntelInstrWRITE extends IntelInstrPROC {

	public IntelInstrWRITE(String label) {
		this.label = label;
		genInstrs();
	}

	@Override
	public void genInstrs() {
		this.instrs = new LinkedList<IntelInstr>();
		this.instrs.add(new IntelInstrPUSH(this.label, SicRegisters.REG_A, null));
		this.instrs.add(new IntelInstrPUSH(null, SicRegisters.REG_B, null));
		this.instrs.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_A, SicAddressing.IMMEDIATE, "0x4", false, null));
		this.instrs.add(new IntelInstrMOV(null, SicAddressing.NONE, SicRegisters.REG_B, SicAddressing.IMMEDIATE, "0x1", false, null));
		this.instrs.add(new IntelInstrINT(null, "0x80", null));
		this.instrs.add(new IntelInstrPOP(null, SicRegisters.REG_B, null));
		this.instrs.add(new IntelInstrPOP(null, SicRegisters.REG_A, null));
		this.instrs.add(new IntelInstrRET(null, null, null));
	}
}