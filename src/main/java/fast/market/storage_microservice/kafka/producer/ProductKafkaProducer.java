package fast.market.storage_microservice.kafka.producer;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class ProductKafkaProducer {
    private final KafkaTemplate<String, Map<Long, Integer>> kafkaTemplate;

    public void sendProductQuantities(Map<Long, Integer> productQuantities){
        kafkaTemplate.send("product-quantity-topic", productQuantities);
    }
}
