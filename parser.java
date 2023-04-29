import java.util.*; 
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.*;

class Parser {

    /**
     * Parses an AUTOSAR file and returns a NodeList of every CONTAINER element.
     * 
     * @param filename the filename of the AUTOSAR file to parse
     * @return a NodeList of every CONTAINER element in the AUTOSAR file
     * @throws NotVaildAutosarFileException if the file is not a valid AUTOSAR file
     * @throws EmptyFileException if the file is empty
     * @throws EmptyAutosarFileException if the AUTOSAR file does not have any containers
     */
    public NodeList parseAutosarFile(String filename) {
        NodeList nodeList = null;
        try {
            checkAutosarFile(filename); // check if it is a valid AUTOSAR file
            File file = new File(filename);
            checkFileisEmpty(file); // check if the file has content
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            nodeList = doc.getElementsByTagName("CONTAINER"); // get every CONTAINER element
            checkAUTOSARisEmpty(nodeList); // check if the AUTOSAR file has containers
        } catch (NotVaildAutosarFileException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nodeList;
    }

    /**
     * Checks if the file at the given file path is a valid AUTOSAR file.
     * 
     * @param filePath the file path of the file to check
     * @throws NotVaildAutosarFileException if the file is not a valid AUTOSAR file
     */
    public void checkAutosarFile(String filePath) throws NotVaildAutosarFileException {
        String extension = getFileExtension(filePath);
        if (!extension.equals("arxml")) {
            throw new NotVaildAutosarFileException("File is not a valid AUTOSAR file.");
        }
    }

    /**
     * Returns the extension of a file path.
     * 
     * @param filePath the file path to get the extension of
     * @return the extension of the file path
     */
    private String getFileExtension(String filePath) {
        int lastDotIndex = filePath.lastIndexOf(".");
        if (lastDotIndex > 0) {
            return filePath.substring(lastDotIndex + 1);
        }
        return "";
    }

    /**
     * Checks if a file is empty.
     * 
     * @param file the file to check
     * @throws EmptyFileException if the file is empty
     */
    private void checkFileisEmpty(File file) {
        if (file.length() == 0) {
            throw new EmptyFileException("Empty file.");
        }
    }

    /**
     * Checks if an AUTOSAR file has any containers.
     * 
     * @param nodeList the NodeList to check
     * @throws EmptyAutosarFileException if the AUTOSAR file does not have any containers
     */
    private void checkAUTOSARisEmpty(NodeList nodeList) {
        if (nodeList.getLength() == 0) {
            throw new EmptyAutosarFileException("Empty AUTOSAR file.");
        }
    }

}
