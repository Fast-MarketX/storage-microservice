package fast.market.storage_microservice.exception;

public class StorageAlreadyExistsException extends Exception{
    public StorageAlreadyExistsException(String message){
        super(message);
    }
}
