import java.util.* ; 
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.* ; 


class Main{
    // Main function that accepts command line arguments
    public static void main(final String[] arguments) {

        // Checking if a filename is provided as a command line argument
        if (arguments.length < 2){
            System.out.println("\nNo filename provided; please provide a filename.\n");
            usage(); // Invoking the usage function
        }

        else {
            // Checking the command line flag
            if (arguments[0].equals("-f")){
                String filename = arguments[1] ; 

                // Creating an instance of the Parser class to parse the XML file
                Parser parser = new Parser() ;
                // Parsing the XML file and getting a NodeList of the elements
                NodeList nodeList = parser.parseAutosarFile(filename);
                if (nodeList.getLength() != 0){
                    // Creating an instance of the Writer class to write modified XML to a file
                    Writer writer = new Writer(nodeList) ;
                    // Getting the index of the last "." in the filename
                    int last_dot_index  = filename.lastIndexOf(".");
                    // Creating a new filename by inserting "_mod" before the last "." in the filename
                    String out_filename = new StringBuilder(filename).insert(last_dot_index , "_mod").toString();
                    // Writing the modified XML to a file with the new filename
                    writer.writeXMLfile(out_filename);  
                }
            }
            else{
                System.out.println("Undefined flag " + arguments[0]);
                usage(); // Invoking the usage function

            }
        }

    }

    // A function that prints usage instructions
    public static void usage(){
        System.out.println("\tUSAGE: -f <filename>");
    }

}