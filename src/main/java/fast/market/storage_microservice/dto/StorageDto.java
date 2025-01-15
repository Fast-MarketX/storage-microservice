package fast.market.storage_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StorageDto {
    private String storageName;
    private String StorageLocation;
    private Map<Long, Integer> productQuantities;
}
