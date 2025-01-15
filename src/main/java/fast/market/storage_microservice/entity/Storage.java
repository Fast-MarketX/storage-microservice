package fast.market.storage_microservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storageId;

    private String storageName;

    private String StorageLocation; // PostGis later

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Long, Integer> productQuantities = new HashMap<>();

    public void addProduct(Long productId, int quantity){
        this.productQuantities.merge(productId, quantity, Integer::sum);
    }

    public void removeProduct(Long productId, int quantity){
        Integer currentQuantity = this.productQuantities.get(productId);
        if (currentQuantity != null){
            int newQuantity = currentQuantity - quantity;
            if(newQuantity <= 0){
                this.productQuantities.remove(productId);
            }else{
                productQuantities.put(productId, newQuantity);
            }
        }
    }
}
