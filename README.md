# Exception Handling Assignment 

## `parser.java`

The code defines a class named Parser that contains methods for parsing an AUTOSAR file and checking its validity.
Method: parseAutosarFile

The `parseAutosarFile` method takes a String parameter filename that represents the path to the AUTOSAR file and returns a NodeList. It first checks if the file is a valid AUTOSAR file by calling the `checkAutosarFile` method. Then it checks if the file has any content or not by calling the `checkFileisEmpty` method. Next, it parses the XML file using the DocumentBuilder class from the javax.xml.parsers package and gets all the **CONTAINER** elements using the `getElementsByTagName` method. Finally, it checks if the parsed AUTOSAR file has containers or not by calling the `checkAUTOSARisEmpty` method. If an exception occurs, it prints the stack trace.
Method: `checkAutosarFile`

The `checkAutosarFile` method takes a String parameter filePath and throws a `NotVaildAutosarFileException` if the file is not a valid AUTOSAR file. It first gets the file extension using the `getFileExtension` method and checks if it is equal to "arxml". If not, it throws the exception.
Method: `getFileExtension`

The `getFileExtension` method takes a String parameter filePath and returns a String representing the file extension. It first finds the last occurrence of "." in the file path using the lastIndexOf method and returns the substring starting from the index after the last ".".
Method: `checkFileisEmpty`

The `checkFileisEmpty` method takes a File parameter file and throws an `EmptyFileException` if the file is empty. It checks if the length of the file is 0 using the length method.
Method: `checkAUTOSARisEmpty`

The `checkAUTOSARisEmpty` method takes a NodeList parameter nodeList and throws an `EmptyAutosarFileException` if the AUTOSAR file has no containers. It checks if the length of the NodeList is 0 using the getLength method.

## `writer.java`

`public Writer(NodeList nodeList)`: The constructor takes a NodeList object as input and initializes an XML document with a root element named "AUTOSAR". The constructor also stores the NodeList object in an instance variable called nodeList.

`public void sortNodelist()`: This method sorts the NodeList object based on the values of the "SHORT-NAME" elements in each node. It uses a hash map to quickly access nodes and sorts them based on their short name. Then, it imports each sorted node into the XML document and appends it to the root element.

`public void writeXMLfile(String fileName)`: This method writes the sorted XML document to a file with the given file name. It first calls the sortNodelist() method to sort the nodes, then it creates a DOMSource object with the sorted XML document and a StreamResult object with a FileWriter that writes to the specified file. It uses a Transformer object to transform the DOMSource to the StreamResult and write the output to the file.

public void appendToDoc(Node node): This method appends a given Node object to the root element of the XML document.

## `exception.java`

`NotVaildAutosarFileException`: This is a checked exception that extends the `Exception` class. It is thrown when an Autosar file is not valid. It has one constructor that takes an error message as a parameter.

`EmptyFileException`: This is an unchecked exception that extends the `RuntimeException` class. It is thrown when a file is empty. It has one constructor that takes an error message as a parameter.

`EmptyAutosarFileException`: This is also an unchecked exception that extends the `RuntimeException` class. It is thrown when an Autosar file is empty. It has one constructor that takes an error message as a parameter.

