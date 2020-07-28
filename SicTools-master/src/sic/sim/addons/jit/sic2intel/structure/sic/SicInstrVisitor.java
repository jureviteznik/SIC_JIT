package sic.sim.addons.jit.sic2intel.structure.sic;

public interface SicInstrVisitor {

	public void visit(SicInstrSTART acceptor);

	public void visit(SicInstrEND acceptor);

	public void visit(SicInstrADD acceptor);

	public void visit(SicInstrADDF acceptor);

	public void visit(SicInstrADDR acceptor);

	public void visit(SicInstrAND acceptor);

	public void visit(SicInstrCLEAR acceptor);

	public void visit(SicInstrCOMP acceptor);

	public void visit(SicInstrCOMPF acceptor);

	public void visit(SicInstrCOMPR acceptor);

	public void visit(SicInstrDIV acceptor);

	public void visit(SicInstrDIVF acceptor);

	public void visit(SicInstrDIVR acceptor);

	public void visit(SicInstrFIX acceptor);

	public void visit(SicInstrFLOAT acceptor);

	public void visit(SicInstrHIO acceptor);

	public void visit(SicInstrJ acceptor);

	public void visit(SicInstrJEQ acceptor);

	public void visit(SicInstrJGT acceptor);

	public void visit(SicInstrJLT acceptor);

	public void visit(SicInstrJSUB acceptor);

	public void visit(SicInstrLDA acceptor);

	public void visit(SicInstrLDB acceptor);

	public void visit(SicInstrLDCH acceptor);

	public void visit(SicInstrLDF acceptor);

	public void visit(SicInstrLDL acceptor);

	public void visit(SicInstrLDS acceptor);

	public void visit(SicInstrLDT acceptor);

	public void visit(SicInstrLDX acceptor);

	public void visit(SicInstrLPS acceptor);

	public void visit(SicInstrMUL acceptor);

	public void visit(SicInstrMULF acceptor);

	public void visit(SicInstrMULR acceptor);

	public void visit(SicInstrNORM acceptor);

	public void visit(SicInstrOR acceptor);

	public void visit(SicInstrRD acceptor);

	public void visit(SicInstrRMO acceptor);

	public void visit(SicInstrRSUB acceptor);

	public void visit(SicInstrSHIFTL acceptor);

	public void visit(SicInstrSHIFTR acceptor);

	public void visit(SicInstrSIO acceptor);

	public void visit(SicInstrSSK acceptor);

	public void visit(SicInstrSTA acceptor);

	public void visit(SicInstrSTB acceptor);

	public void visit(SicInstrSTCH acceptor);

	public void visit(SicInstrSTF acceptor);

	public void visit(SicInstrSTI acceptor);

	public void visit(SicInstrSTL acceptor);

	public void visit(SicInstrSTS acceptor);

	public void visit(SicInstrSTSW acceptor);

	public void visit(SicInstrSTT acceptor);

	public void visit(SicInstrSTX acceptor);

	public void visit(SicInstrSUB acceptor);

	public void visit(SicInstrSUBF acceptor);

	public void visit(SicInstrSUBR acceptor);

	public void visit(SicInstrSVC acceptor);

	public void visit(SicInstrTD acceptor);

	public void visit(SicInstrTIO acceptor);

	public void visit(SicInstrTIX acceptor);

	public void visit(SicInstrTIXR acceptor);

	public void visit(SicInstrWD acceptor);

	public void visit(SicInstrRESB acceptor);

	public void visit(SicInstrRESW acceptor);

	public void visit(SicInstrBYTE acceptor);

	public void visit(SicInstrWORD acceptor);

	public void visit(SicInstrEQU acceptor);

	public void visit(SicInstrWRITE acceptor);
	
	public void visit(SicInstrREAD acceptor);

}
