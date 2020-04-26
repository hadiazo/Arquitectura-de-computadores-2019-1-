package Ensamblador;

import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Info {
    static boolean ok = true;
    static ArrayList<String> section = new ArrayList<>();
    static ArrayList<String> label = new ArrayList<>();
    static ArrayList<String> mnemo = new ArrayList<>();
    static ArrayList<String> operands = new ArrayList<>();
    static ArrayList<String> instrSymbols = new ArrayList<>();
    static String[] dataSymbols;
    static int linesOfCodeSection;
    static int [] nums;
    
    static void fill(ArrayList<String> list, String cell) {
        if (cell == null || cell.equals(" ")) {
            list.add("");
        } else {
            list.add(cell);
        }
    }
    
    static void read() {
        String auxS = "";            
        String[] fields;
        try {
            for (String line : Input.text.split("\\n")) {
                fields = line.split("\t");
                fill(section, fields[1]);
                fill(label, fields[2]);
                fill(mnemo, fields[3]);
                fill(operands, fields[4]);
            }
        } catch (Exception e) {
            ok = false;
            JOptionPane.showMessageDialog(null, "Error al leer la entrada. Intente otra vez.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    static void checkMnemos() {
        for (int i=0; i<nums.length; i++) {
            switch(mnemo.get(i)) {
                case "load":
                    nums[i] = (0x000); break;
                case "store":
                    nums[i] = (0x100); break;
                case "add":
                    nums[i] = (0x200); break;
                case "sub":
                    nums[i] = (0x300); break;
                case "input":
                    nums[i] = (0x400); break;
                case "output":
                    nums[i] = (0x500); break;
                case "jpos":
                    nums[i] = (0x600); break;
                case "jneg":
                    nums[i] = (0x700); break;
                case "jz":
                    nums[i] = (0x800); break;
                case "jnz":
                    nums[i] = (0x900); break;
                case "jmp":
                    nums[i] = (0xA00); break;
                case "halt":
                    nums[i] = (0xB00); break;
                default:
                    nums[i] = -1;
            }
            if(label.get(i).length()>=1) {
                instrSymbols.add(label.get(i));
            }
        }
    }
    
    static void write(String path, String input, boolean firstTime, boolean lastTime, boolean numGE0) {
        FileWriter writer;
        try {
            if (firstTime) {
                writer = new FileWriter(path);
            } else {
                writer = new FileWriter(path, true);
            }
            writer.write(input);
            if(numGE0 && !lastTime) {writer.write(",\r\n");}
            writer.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado. Reinicie el programa.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
    
    static void getOutput () {
        for (int i=0; i<nums.length; i++) {
            if(operands.get(i).length()>=1) {
                for (int j=0; j<instrSymbols.size(); j++) {
                    if(instrSymbols.get(j).equals(operands.get(i))) {
                        nums[i] += label.indexOf(instrSymbols.get(j));
                    }
                }
                for (int j=0; j<dataSymbols.length; j++) {
                    if(dataSymbols[j].equals(operands.get(i))) {
                        nums[i] += j;
                    }
                }
            }
            if (nums[i] >= 0) {
            	String hex = String.format("0x%03X", nums[i]);
                write("output_cpp.txt", hex, i==0, i==nums.length-1, nums[i] >= 0);
            	hex = "X\""+hex.substring(2, hex.length())+"\"";
            	write("output_vhdl.txt", hex, i==0, i==nums.length-1, nums[i] >= 0);
            } else {
            	if (i==0) {
                    write("output_cpp.txt","", i==0, i==nums.length-1, nums[i] >= 0);
                    write("output_vhdl.txt","", i==0, i==nums.length-1, nums[i] >= 0);
            	}
            }
        }
    }
}
