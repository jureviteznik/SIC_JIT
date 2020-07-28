package sic.sim.addons.jit;

import java.util.LinkedList;

public class ThrowawayForHeaders {
 
    private native void execute(IntelInstr[] mainBlock, IntelInstr[] dataBlock);

    private native void executeAsm(String s);
    static {
        System.loadLibrary("sic_jit_pkg");
    }

    public static void main(String[] args){
        new ThrowawayForHeaders().executeAsm(null);
    }

    public class IntelInstr {

        /** label */
        public String label;
    
        public String toString(String l, String i, String op, SicInstr src) {
            //EDITED BY JURE VITEZNIK, WE DONT NEED DEBUGING
            //if (sic2intel.Main.debugOuput) {
            if(false){
                if (src != null) {
                    return String.format("%4$s%1$-16s%2$-8s%3$s\n", l, i, op, src.toString());
                } else {
                    return String.format("%1$-16s%2$-8s%3$s\n", l, i, op);
                }
            } else {
                return String.format("%1$-16s%2$-8s%3$s\n", l, i, op);
            }
        }
    
        public String setAddressing(Integer addrType, String op) {
            return "lmao";
        }
    
        public String setAddressing(Integer addrType, Integer op) {
            return setAddressing(addrType, op.toString());
        }
        
        public String setPtrSize(Integer type) {
            return "xD";
        }
    
    }

    public abstract class SicInstr {

        /**
         * Instruction label. Every instruction can have a label.
         */
        public String label;
    
        public String toString(String instr, String identifier) {
            String l = label != null ? label : "";
    
            return toString(l, instr, identifier);
        }
        
        public String toString(String l, String i, String op) {
            return String.format("%1$-16s%2$-8s%3$s\n", "#" + l, i, op);
        }	
    }

    
    
}