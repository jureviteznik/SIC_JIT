package sic.sim.addons.jit.sic2intel.structure;


import java.util.HashMap;
import java.util.HashSet;

import sic.sim.addons.jit.sic2intel.structure.intel.IntelInstrDATA;
import sic.sim.addons.jit.sic2intel.structure.sic.SicData;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrADD;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrADDF;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrADDR;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrAND;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrBYTE;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrCLEAR;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrCOMP;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrCOMPF;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrCOMPR;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrDIV;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrDIVF;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrDIVR;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrEND;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrEQU;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrFIX;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrFLOAT;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrHIO;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrJ;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrJEQ;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrJGT;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrJLT;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrJSUB;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrLDA;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrLDB;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrLDCH;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrLDF;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrLDL;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrLDS;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrLDT;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrLDX;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrLPS;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrMUL;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrMULF;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrMULR;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrNORM;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrOR;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrRD;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrREAD;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrRESB;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrRESW;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrRMO;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrRSUB;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSHIFTL;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSHIFTR;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSIO;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSSK;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSTA;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSTART;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSTB;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSTCH;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSTF;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSTI;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSTL;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSTS;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSTSW;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSTT;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSTX;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSUB;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSUBF;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSUBR;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrSVC;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrTD;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrTIO;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrTIX;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrTIXR;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrVisitor;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrWD;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrWORD;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstrWRITE;

/**
 * 
 * Visitor responsible for the analysis of variables.
 * 
 * @author benjamin
 *
 */
public class DataInspector implements SicInstrVisitor { 
	
	/** A hashtable containing variable labels and their respective types */
	public static HashMap<String, Integer> dataTypes;
	
	/** A hashtable containing variable labels and their respective values */
	public static HashMap<String, String> dataValues;
	
	/** A set of equ variables. */
	public static HashSet<String> equSet;
	
	public DataInspector() {
		dataTypes = new HashMap<String, Integer>();
		dataValues = new HashMap<String, String>();
		equSet = new HashSet<String>();
	}
	
	@Override
	public void visit(SicInstrSTART acceptor) {
		//System.out.println("..:: START ::..");
	}
	
	@Override
	public void visit(SicInstrEND acceptor) {
		//System.out.println("..:: END ::..");
	}
	
	@Override
	public void visit(SicInstrADD acceptor) {
		//System.out.println("..:: ADD ::..");
	}

	@Override
	public void visit(SicInstrADDF acceptor) {
		//System.out.println("..:: ADDF ::..");
	}

	@Override
	public void visit(SicInstrADDR acceptor) {
		//System.out.println("..:: ADDR ::..");
	}

	@Override
	public void visit(SicInstrAND acceptor) {
		//System.out.println("..:: AND ::..");
	}

	@Override
	public void visit(SicInstrCLEAR acceptor) {
		//System.out.println("..:: CLEAR ::..");
	}

	@Override
	public void visit(SicInstrCOMP acceptor) {
		//System.out.println("..:: COMP ::..");
	}

	@Override
	public void visit(SicInstrCOMPF acceptor) {
		//System.out.println("..:: COMPF ::..");
	}

	@Override
	public void visit(SicInstrCOMPR acceptor) {
		//System.out.println("..:: COMPR ::..");
	}

	@Override
	public void visit(SicInstrDIV acceptor) {
		//System.out.println("..:: DIV ::..");
	}

	@Override
	public void visit(SicInstrDIVF acceptor) {
		//System.out.println("..:: DIVF ::..");
	}

	@Override
	public void visit(SicInstrDIVR acceptor) {
		//System.out.println("..:: DIVR ::..");
	}

	@Override
	public void visit(SicInstrFIX acceptor) {
		//System.out.println("..:: FIX ::..");
	}

	@Override
	public void visit(SicInstrFLOAT acceptor) {
		//System.out.println("..:: FLOAT ::..");
		
		IntelInstrDATA tmp1 = new IntelInstrDATA(SicData.RESW, "1");
		dataTypes.put(tmp1.getLabel(), SicData.RESW);
	}

	@Override
	public void visit(SicInstrHIO acceptor) {
		//System.out.println("..:: HIO ::..");
	}

	@Override
	public void visit(SicInstrJ acceptor) {
		//System.out.println("..:: J ::..");
	}

	@Override
	public void visit(SicInstrJEQ acceptor) {
		//System.out.println("..:: JEQ ::..");
	}

	@Override
	public void visit(SicInstrJGT acceptor) {
		//System.out.println("..:: JGT ::..");
	}

	@Override
	public void visit(SicInstrJLT acceptor) {
		//System.out.println("..:: JLT ::..");
	}

	@Override
	public void visit(SicInstrJSUB acceptor) {
		//System.out.println("..:: JSUB ::..");
	}

	@Override
	public void visit(SicInstrLDA acceptor) {
		//System.out.println("..:: LDA ::..");
	}

	@Override
	public void visit(SicInstrLDB acceptor) {
		//System.out.println("..:: LDB ::..");
	}

	@Override
	public void visit(SicInstrLDCH acceptor) {
		//System.out.println("..:: LDCH ::..");	
	}

	@Override
	public void visit(SicInstrLDF acceptor) {
		//System.out.println("..:: LDF ::..");
	}

	@Override
	public void visit(SicInstrLDL acceptor) {
		//System.out.println("..:: LDL ::..");		
	}

	@Override
	public void visit(SicInstrLDS acceptor) {
		//System.out.println("..:: LDS ::..");
	}

	@Override
	public void visit(SicInstrLDT acceptor) {
		//System.out.println("..:: LDT ::..");
	}

	@Override
	public void visit(SicInstrLDX acceptor) {
		//System.out.println("..:: LDX ::..");
	}

	@Override
	public void visit(SicInstrLPS acceptor) {
		//System.out.println("..:: LPS ::..");
	}

	@Override
	public void visit(SicInstrMUL acceptor) {
		//System.out.println("..:: MUL ::..");
	}

	@Override
	public void visit(SicInstrMULF acceptor) {
		//System.out.println("..:: MULF ::..");
	}

	@Override
	public void visit(SicInstrMULR acceptor) {
		//System.out.println("..:: MULR ::..");
	}

	@Override
	public void visit(SicInstrNORM acceptor) {
		//System.out.println("..:: NORM ::..");
	}

	@Override
	public void visit(SicInstrOR acceptor) {
		//System.out.println("..:: OR ::..");
	}

	@Override
	public void visit(SicInstrRD acceptor) {
		//System.out.println("..:: RD ::..");
	}

	@Override
	public void visit(SicInstrRMO acceptor) {
		//System.out.println("..:: RMO ::..");
	}

	@Override
	public void visit(SicInstrRSUB acceptor) {
		//System.out.println("..:: RSUB ::..");
	}

	@Override
	public void visit(SicInstrSHIFTL acceptor) {
		//System.out.println("..:: SHIFTL ::..");
	}

	@Override
	public void visit(SicInstrSHIFTR acceptor) {
		//System.out.println("..:: SHIFTR ::..");
	}

	@Override
	public void visit(SicInstrSIO acceptor) {
		//System.out.println("..:: SIO ::..");
	}

	@Override
	public void visit(SicInstrSSK acceptor) {
		//System.out.println("..:: SSK ::..");
	}

	@Override
	public void visit(SicInstrSTA acceptor) {
		//System.out.println("..:: STA ::..");
	}

	@Override
	public void visit(SicInstrSTB acceptor) {
		//System.out.println("..:: STB ::..");
	}

	@Override
	public void visit(SicInstrSTCH acceptor) {
		//System.out.println("..:: STCH ::..");
	}

	@Override
	public void visit(SicInstrSTF acceptor) {
		//System.out.println("..:: STF ::..");
	}

	@Override
	public void visit(SicInstrSTI acceptor) {
		//System.out.println("..:: STI ::..");
	}

	@Override
	public void visit(SicInstrSTL acceptor) {
		//System.out.println("..:: STL ::..");
	}

	@Override
	public void visit(SicInstrSTS acceptor) {
		//System.out.println("..:: STS ::..");
	}

	@Override
	public void visit(SicInstrSTSW acceptor) {
		//System.out.println("..:: STSW ::..");
	}

	@Override
	public void visit(SicInstrSTT acceptor) {
		//System.out.println("..:: STT ::..");
	}

	@Override
	public void visit(SicInstrSTX acceptor) {
		//System.out.println("..:: STX ::..");
	}

	@Override
	public void visit(SicInstrSUB acceptor) {
		//System.out.println("..:: SUB ::..");
	}

	@Override
	public void visit(SicInstrSUBF acceptor) {
		//System.out.println("..:: SUBF ::..");
	}

	@Override
	public void visit(SicInstrSUBR acceptor) {
		//System.out.println("..:: SUBR ::..");
	}

	@Override
	public void visit(SicInstrSVC acceptor) {
		//System.out.println("..:: SVC ::..");
	}

	@Override
	public void visit(SicInstrTD acceptor) {
		//System.out.println("..:: TD ::..");
	}

	@Override
	public void visit(SicInstrTIO acceptor) {
		//System.out.println("..:: TIO ::..");
	}

	@Override
	public void visit(SicInstrTIX acceptor) {
		//System.out.println("..:: TIX ::..");
	}

	@Override
	public void visit(SicInstrTIXR acceptor) {
		//System.out.println("..:: TIXR ::..");
	}

	@Override
	public void visit(SicInstrWD acceptor) {
		//System.out.println("..:: WD ::..");
	}

	@Override
	public void visit(SicInstrRESB acceptor) {
		//System.out.println("..:: RESB ::..");

		if (acceptor.label != null) {
			dataTypes.put(acceptor.label, SicData.RESB);
			dataValues.put(acceptor.label, acceptor.value);
		}
	}

	@Override
	public void visit(SicInstrRESW acceptor) {
		//System.out.println("..:: RESW ::..");
		
		if (acceptor.label != null) {
			dataTypes.put(acceptor.label, SicData.RESW);
			dataValues.put(acceptor.label, acceptor.value);
		}
	}

	@Override
	public void visit(SicInstrBYTE acceptor) {
		//System.out.println("..:: BYTE ::..");
		
		if (acceptor.label != null) {
			dataTypes.put(acceptor.label, SicData.BYTE);
			dataValues.put(acceptor.label, acceptor.value);
		}
	}

	@Override
	public void visit(SicInstrWORD acceptor) {
		//System.out.println("..:: WORD ::..");
		
		if (acceptor.label != null) {
			dataTypes.put(acceptor.label, SicData.WORD);
			dataValues.put(acceptor.label, acceptor.value);
		}
	}
	
	@Override
	public void visit(SicInstrEQU acceptor) {
		//System.out.println("..:: EQU ::..");
		
		equSet.add(acceptor.label);
	}

	@Override
	public void visit(SicInstrWRITE acceptor) {
		//System.out.println("..:: WRITE ::..");
	}
	
	@Override
	public void visit(SicInstrREAD acceptor) {
		//System.out.println("..:: READ ::..");
	}
}
