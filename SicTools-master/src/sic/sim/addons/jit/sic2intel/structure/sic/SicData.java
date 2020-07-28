package sic.sim.addons.jit.sic2intel.structure.sic;

public class SicData {

	public static final int BYTE = 0;
	
	public static final int WORD = 1; 
	
	public static final int RESB = 2; 
	
	public static final int RESW = 3;
	
	public static final int FLOAT = 4;
	
	// not really a SIC data type - used to create strings only on an Intel
	public static final int STRING = 5;
	
	/*
	 *  again - not really a data type per se - used here only to
	 *  distinguish beetween EQU and other data types
	 */
	public static final int EQU = 6;
	
}
