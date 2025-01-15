package fast.market.storage_microservice.controller;

import fast.market.storage_microservice.dto.ProductProcessDto;
import fast.market.storage_microservice.dto.StorageDto;
import fast.market.storage_microservice.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/storage")
public class StorageController {
    @Autowired
    private StorageService storageService;

    @PostMapping("/create")
    public ResponseEntity<StorageDto> createStorage(@RequestBody StorageDto storageDto){
        storageService.createStorage(storageDto);
        return new ResponseEntity<>(storageDto, HttpStatus.CREATED);
    }

    @GetMapping("/get/{storageId}")
    public ResponseEntity<StorageDto> getStorageById(@PathVariable Long storageId){
        StorageDto storage = storageService.getStorageById(storageId);
        return new ResponseEntity<>(storage, HttpStatus.OK);
    }

    @PatchMapping("/update/{storageId}")
    public ResponseEntity<StorageDto> updateStorage(@RequestBody StorageDto storageDto, @PathVariable Long storageId){
        StorageDto updateStorage = storageService.updateStorage(storageDto, storageId);
        return new ResponseEntity<>(updateStorage, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{storageId}")
    public ResponseEntity<String> deleteStorage(@PathVariable Long storageId){
        storageService.deleteStorage(storageId);
        return new ResponseEntity<>("Storage was successfully deleted", HttpStatus.NO_CONTENT);
    }


    @PostMapping("/addProduct")
    public ResponseEntity<ProductProcessDto> addProductToStorage(@RequestBody ProductProcessDto productProcessDto){
        storageService.addProductToStorage(productProcessDto);
        return new ResponseEntity<>(productProcessDto, HttpStatus.OK);
    }

    @PostMapping("/removeProduct")
    public ResponseEntity<ProductProcessDto> removeProductFromStorage(@RequestBody ProductProcessDto productProcessDto){
        storageService.removeProductFromStorage(productProcessDto);
        return new ResponseEntity<>(productProcessDto, HttpStatus.OK);
    }

    @GetMapping("/getProductsFromStorage/{storageId}")
    public ResponseEntity<Map<Long,Integer>> getProductsFromStorage(@PathVariable Long storageId){
        Map<Long, Integer> products = storageService.getProductsFromStorage(storageId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
