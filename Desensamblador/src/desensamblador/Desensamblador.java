package desensamblador;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Harold Díaz
 */
public class Desensamblador {
    static String[] mnemo;
    
    static String readNumbers() {
        String text = "";
        try {
            for (@SuppressWarnings("unused") javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al abrir el programa. ", "Error", JOptionPane.ERROR_MESSAGE);
        }
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
        fileChooser.setFileFilter(filter);
        fileChooser.showOpenDialog(fileChooser);
        try {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            DataInputStream dis = new DataInputStream(new FileInputStream(path));
            BufferedReader bf = new BufferedReader(new InputStreamReader(dis));
            String line;
            while ((line = bf.readLine()) != null) {
                if (line.endsWith(",")) {
                    line = line.substring(0, line.length()-1);
                }
                if (line.endsWith("\"")) {
                    line = line.substring(0, line.length()-1);
                }
                line = line.substring(2);
                text = text.concat(line.toLowerCase()+";");
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Archivo no encontrado. Intente otra vez. ", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Archivo no encontrado. Intente otra vez. ", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al leer archivo. Intente otra vez. ", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return text;
    }
    
    static void checkMnemos(int number, int index) {
        int quotient = number / 0x100;
        //int remainder = number % 0x100;
        switch (quotient) {
            case 0:
                mnemo[index] = "load"; break;
            case 1:
                mnemo[index] = "store"; break;
            case 2:
                mnemo[index] = "add"; break;
            case 3:
                mnemo[index] = "sub"; break;
            case 4:
                mnemo[index] = "input"; break;
            case 5:
                mnemo[index] = "output"; break;
            case 6:
                mnemo[index] = "jpos"; break;
            case 7:
                mnemo[index] = "jneg"; break;
            case 8:
                mnemo[index] = "jz"; break;
            case 9:
                mnemo[index] = "jnz"; break;
            case 10:
                mnemo[index] = "jmp"; break;
            case 11:
                mnemo[index] = "halt"; break;
            default:
                mnemo[index] = "N/A";
        }
    }
    
    static void write(String input, boolean firstTime) {
        FileWriter writer;
        try {
            if (firstTime) {
                writer = new FileWriter("output.csv");
            } else {
                writer = new FileWriter("output.csv", true);
            }
            writer.write(input+"\r\n");
            writer.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado. Reinicie el programa.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    public static void mainFunc(String[] args) {
        String str = readNumbers();
        String [] hex = str.split(";");
        final int NUM_INSTR = hex.length;
        int[] numbers = new int[NUM_INSTR];
        mnemo = new String[NUM_INSTR];

        /*try {
            write("HLL;SECTION;LABEL;MNEMO;OPERANDS;COMMENTS;ADDR;CONT", true);
            for (int i=0; i<NUM_INSTR; i++) {
                numbers[i] = Integer.parseInt(hex[i], 16);
                checkMnemos(numbers[i], i);
                String section = (i==0)? ".code":"";
                str = ";"+section+";;"+mnemo[i]+";"+";;"+String.format("0x%02X", i).substring(2)+";"+String.format("0x%03X", numbers[i]);
                write(str, false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer archivo. Intente otra vez. ", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        try {
            File objetofile = new File ("output.csv");
            Desktop.getDesktop().open(objetofile);
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "Error al generar archivo. Intente otra vez. ", "Error", JOptionPane.ERROR_MESSAGE);
        }*/
    }
}
