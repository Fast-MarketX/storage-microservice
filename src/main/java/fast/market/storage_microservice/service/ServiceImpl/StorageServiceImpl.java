package fast.market.storage_microservice.service.ServiceImpl;

import fast.market.storage_microservice.dto.ProductProcessDto;
import fast.market.storage_microservice.dto.StorageDto;
import fast.market.storage_microservice.entity.Storage;
import fast.market.storage_microservice.exception.StorageAlreadyExistsException;
import fast.market.storage_microservice.exception.StorageNotFoundException;
import fast.market.storage_microservice.mapper.StorageMapper;
import fast.market.storage_microservice.repository.StorageRepository;
import fast.market.storage_microservice.service.StorageService;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private StorageMapper storageMapper;

    String storageNotFoundExMessage = "Storage with the given ID does not exist";
    String storageAlreadyExistsExMessage = "Storage with the given ID already exists";

    @Override
    @SneakyThrows
    @Transactional
    public StorageDto createStorage(StorageDto storageDto) {
        if (storageRepository.existsByStorageName(storageDto.getStorageName())){
            throw new StorageAlreadyExistsException(storageAlreadyExistsExMessage);
        }

        Storage createdStorage = storageMapper.StorageDtoToStorage(storageDto);
        storageRepository.save(createdStorage);

        return storageMapper.StorageToStorageDto(createdStorage);
    }

    @Override
    @SneakyThrows
    @Transactional(readOnly = true)
    public StorageDto getStorageById(Long storageId) {
        Storage storage = storageRepository.findById(storageId).orElseThrow(() -> new StorageNotFoundException(storageNotFoundExMessage));
        return storageMapper.StorageToStorageDto(storage);
    }

    @Override
    @Transactional
    @SneakyThrows
    public StorageDto updateStorage(StorageDto storageDto, Long storageId) {
        Storage existingStorage = storageRepository.findById(storageId).orElseThrow(() -> new StorageNotFoundException(storageNotFoundExMessage));

        BeanUtils.copyProperties(storageDto, existingStorage, "storageId");

        Storage updatedStorage = storageRepository.save(existingStorage);

        return storageMapper.StorageToStorageDto(updatedStorage);
    }

    @Override
    @SneakyThrows
    public void deleteStorage(Long storageId) {
        if(!storageRepository.existsById(storageId)){
            throw new StorageNotFoundException(storageNotFoundExMessage);
        }
        storageRepository.deleteById(storageId);
    }

    @Override
    @SneakyThrows
    @Transactional
    public void addProductToStorage(ProductProcessDto productProcessDto) {
        Long storageId = productProcessDto.getStorageId();
        Long productId = productProcessDto.getProductId();
        int quantity = productProcessDto.getQuantity();

        Storage storage = storageRepository.findById(storageId).orElseThrow(() -> new StorageNotFoundException(storageNotFoundExMessage));
        storage.addProduct(productId, quantity);

        storageRepository.save(storage);
    }

    @Override
    @SneakyThrows
    @Transactional
    public void removeProductFromStorage(ProductProcessDto productProcessDto) {
        Long storageId = productProcessDto.getStorageId();
        Long productId = productProcessDto.getProductId();
        int quantity = productProcessDto.getQuantity();

        Storage storage = storageRepository.findById(storageId).orElseThrow(() -> new StorageNotFoundException(storageNotFoundExMessage));
        storage.removeProduct(productId, quantity);

        storageRepository.save(storage);
    }

    @Override
    @SneakyThrows
    public Map<Long, Integer> getProductsFromStorage(Long storageId) {
        Storage storage = storageRepository.findById(storageId).orElseThrow(() -> new StorageNotFoundException(storageNotFoundExMessage));
        return storage.getProductQuantities();
    }
}
