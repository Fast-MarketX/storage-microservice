package fast.market.storage_microservice.exception;

public class StorageNotFoundException extends Exception {
    public StorageNotFoundException(String message){
        super(message);
    }
}
