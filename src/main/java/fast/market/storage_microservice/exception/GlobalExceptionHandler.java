package fast.market.storage_microservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(StorageNotFoundException.class)
    public ResponseEntity<Object> handleStorageNotFoundException(StorageNotFoundException storageNotFoundException){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(storageNotFoundException.getMessage());
    }

    @ExceptionHandler(StorageAlreadyExistsException.class)
    public ResponseEntity<Object> handleStorageAlreadyExistsException(StorageAlreadyExistsException storageAlreadyExistsException){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(storageAlreadyExistsException.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException runtimeException){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(runtimeException.getMessage());
    }
}
