package fast.market.storage_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductProcessDto {
    private Long storageId;
    private Long productId;
    private int quantity;
}
