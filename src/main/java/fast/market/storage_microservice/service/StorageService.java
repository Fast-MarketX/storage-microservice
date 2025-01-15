package fast.market.storage_microservice.service;

import fast.market.storage_microservice.dto.ProductProcessDto;
import fast.market.storage_microservice.dto.StorageDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface StorageService {
    StorageDto createStorage(StorageDto storageDto);
    StorageDto getStorageById(Long storageId);
    StorageDto updateStorage(StorageDto storageDto, Long storageId);
    void deleteStorage(Long storageId);

    void addProductToStorage(ProductProcessDto productProcessDto);
    void removeProductFromStorage(ProductProcessDto productProcessDto);
    Map<Long, Integer> getProductsFromStorage(Long storageId);

}
