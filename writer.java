import java.util.* ; 
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.* ; 
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


class Writer{

    private Document document ; 
    private NodeList nodeList ;
    private Element root ;

    public Writer(NodeList nodeList){

        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.newDocument();
            
            root = document.createElement("AUTOSAR"); 
            document.appendChild(root) ;
            this.nodeList = nodeList ; 
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sortNodelist(){ // sort node with short name and append to document 
        
        // create a hash map to quickly access nodes 
        Map <String , Integer>   items =  new HashMap<String , Integer>() ; 

        for (int itr = 0; itr < nodeList.getLength(); itr++) {  

            Node node = nodeList.item(itr);
            String shortName =  ((Element) node).getElementsByTagName("SHORT-NAME").item(0).getTextContent() ;            
            
            items.put(shortName , itr) ; // add shortname and order to the hashmap     
        }

        // list of hash map keys to sort with
        List<String> keys = new ArrayList<String>(items.keySet());
        Collections.sort(keys); // sort keys

        for( String key : keys){
            // import the node (to overcome different document error)
            Node imported_node = document.importNode(nodeList.item(items.get(key)), true);
            appendToDoc(imported_node); // append to document
        }

    }

    public void writeXMLfile(String fileName ){

        try {

            TransformerFactory transformerFactory = TransformerFactory.newInstance() ; 
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); // set encoding to utf-8
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // enable indentation

            sortNodelist(); // sort nodes

            DOMSource source = new DOMSource(document);

            FileWriter writer = new FileWriter(new File(fileName));
            StreamResult result = new StreamResult(writer);

            transformer.transform(source, result);
            System.out.println("Output has been written to " + fileName);
        }

        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void appendToDoc(Node node){

        root.appendChild(node) ;

    }

}