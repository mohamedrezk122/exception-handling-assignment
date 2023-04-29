
// a checked exception 
class NotVaildAutosarFileException extends Exception {  
    public NotVaildAutosarFileException(String errorMessage) {  
        super(errorMessage);  
    }  
}  


// an unchecked exception
class EmptyFileException extends RuntimeException {  
    public EmptyFileException(String errorMessage) {  
        super(errorMessage);  
    }  
}  

// an unchecked exception
class EmptyAutosarFileException extends RuntimeException {  
    public EmptyAutosarFileException(String errorMessage) {  
        super(errorMessage);  
    }  
}  