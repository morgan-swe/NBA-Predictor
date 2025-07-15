package core;
import java.io.File;
/**
 * Raphael Dela Pena
 * Morgan Lee
 * Sean Bartolome
 *
 * April 22, 2025
 *
 * T05
 */

public class Main {
    public static void main(String[] args) {
        if(args.length > 1) { // checks if there's only 1 file given as an argument
            System.err.println("Expected only one argument for filename");
        }
        if(args.length == 1) {
            String filename = args[0];
            File file  = new File(args[0]);  // create new file
            if(!file.exists() || !file.canRead()){ // checks if the file is readable and exists
                System.err.println("Cannot load file");
                System.exit(1);
            }
            Menu.menuLoop(file); //returns file if given
        }else {


            Menu.menuLoop(null); //returns no file if not given
        }
    }
}
