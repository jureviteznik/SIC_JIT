package sic.sim.addons.jit.sic2intel.structure.intel;

import sic.sim.addons.jit.sic2intel.structure.sic.SicData;
import sic.sim.addons.jit.sic2intel.structure.sic.SicInstr;

public class IntelInstrDATA extends IntelInstr {
	
	/** Storage type */
	private Integer type;
	
	/** 
	 * RESW, RESB - number of words/bytes to allocate
	 * WORD, BYTE - word/byte to store 
	 */
	private String value;
	
	/** Counter of auto generated labels */
	private static int counter;
	
	private SicInstr srcInstr;

	/** User defined label */
	public IntelInstrDATA(String label, Integer type, String value, SicInstr srcInstr) {
		this.label = label;
		this.type = type;
		this.value = value;
		this.srcInstr = srcInstr;
	}
	
	/** Auto generated label */
	public IntelInstrDATA(Integer type, String value) {
		this.label = "_t" + IntelInstrDATA.counter;
		this.type = type;
		this.value = value;
		
		IntelInstrDATA.counter++;
	}
	
	public static String genLabel() {
		IntelInstrDATA.counter++;
		return "_t" + (IntelInstrDATA.counter - 1);
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public static Integer getCounter() {
		return counter;
	}
	
	/**EDITED BY JURE VITEZNIK */
	@Override
	public String toString() {
		// label
		String l = (label != null ? label + ":" : "");// + "\t\t";
		
		// type
		String t = "";// + "\t\t";
		
		// value		
		String v = "";
		
		if (this.type == SicData.BYTE || this.type == SicData.WORD) {
			if (this.value.charAt(0) == 'X') {
				v = "0x" + (this.value.substring(1)).replaceAll("'", "");
			} else if (this.value.charAt(0) == 'C') {				
				for (int i = this.value.length() - 2; i >= 2; i--) {
					if (i == 2) {
						v += "0x" + (byte)(this.value.charAt(i));
					} else {
						v += "0x" + (byte)(this.value.charAt(i)) + ", ";
					}
				}
			} else {
				v = this.value;
			}
		} else {
			v = this.value;
		}
		
		switch (this.type) {
		case SicData.BYTE:
			t = ".db";
			break;
		case SicData.WORD:
			t = ".dd"; 	//THIS IS CORRECT! MAYBE YOU MUST USE .DD EVERYWHERE
			break;
		case SicData.RESB:
			t = ".db";
			break;
		case SicData.RESW:
			t = ".db";
			v = "" + (Integer.parseInt(this.value) * 4);
			break;
		case SicData.FLOAT:
			//t = ".double"; //NO IDEJA WHAT TO USE HERE....
			t = ".db";
			break;
		case SicData.STRING:
			t = ".db"; //MIGHT BE WRONG! MAYBE USE '..' AND NOT ".."
			v = ("\"" + v + "\"");
			break;
		default:
			t = "";
		}
		
		return super.toString(l, t, v, srcInstr);
	}


	/** ORIGINAL VERSION
	 * @Override
	public String toString() {
		// label
		String l = (label != null ? label + ":" : "");// + "\t\t";
		
		// type
		String t = "";// + "\t\t";
		
		// value		
		String v = "";
		
		if (this.type == SicData.BYTE || this.type == SicData.WORD) {
			if (this.value.charAt(0) == 'X') {
				v = "0x" + (this.value.substring(1)).replaceAll("'", "");
			} else if (this.value.charAt(0) == 'C') {				
				for (int i = this.value.length() - 2; i >= 2; i--) {
					if (i == 2) {
						v += "0x" + (byte)(this.value.charAt(i));
					} else {
						v += "0x" + (byte)(this.value.charAt(i)) + ", ";
					}
				}
			} else {
				v = this.value;
			}
		} else {
			v = this.value;
		}
		
		switch (this.type) {
		case SicData.BYTE:
			t = ".byte";
			break;
			// return (label != null ? this.label + ":" : "") + "\t\t" + ".byte" + "\t\t" + value;
		case SicData.WORD:
			t = ".long";
			break;
			// return (label != null ? this.label + ":" : "") + "\t\t" + ".long" + "\t\t" + value;
		case SicData.RESB:
			t = ".byte";
			break;
			// return (label != null ? this.label + ":" : "") + "\t\t" + ".space" + "\t\t" + this.value;
		case SicData.RESW:
			t = ".space";
			v = "" + (Integer.parseInt(this.value) * 4);
			break;
			// return (label != null ? this.label + ":" : "") + "\t\t" + ".space" + "\t\t" + Integer.parseInt(this.value) * 4;
		case SicData.FLOAT:
			t = ".double";
			break;
			// return (label != null ? this.label + ":" : "") + "\t\t" + ".double" + "\t\t" + value;
		case SicData.STRING:
			t = ".string";
			v = ("\"" + v + "\"");
			break;
			// return (label != null ? this.label + ":" : "") + "\t\t" + ".string" + "\t\t" + "\"" + value + "\"";
		default:
			t = "";
		}
		
		return super.toString(l, t, v, srcInstr);
	}
	 */

}