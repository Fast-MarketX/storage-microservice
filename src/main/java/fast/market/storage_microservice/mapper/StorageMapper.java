package fast.market.storage_microservice.mapper;

import fast.market.storage_microservice.dto.StorageDto;
import fast.market.storage_microservice.entity.Storage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StorageMapper {
    StorageDto StorageToStorageDto(Storage storage);
    Storage StorageDtoToStorage(StorageDto storageDto);
}
