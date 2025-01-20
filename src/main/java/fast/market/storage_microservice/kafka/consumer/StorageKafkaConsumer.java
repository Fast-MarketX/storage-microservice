package fast.market.storage_microservice.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import fast.market.storage_microservice.events.ListOfProductsRequest;
import fast.market.storage_microservice.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StorageKafkaConsumer {
    @Autowired
    private StorageService storageService;
    private final String RESPONSE_TOPIC = "product-response";

    @KafkaListener(topics = "product-events", groupId = "storage-group")
    public void handleProductEvent(String jsonEvent){ // later add switch(eventType) to handle different events
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            Map<String, Object> eventMap = objectMapper.readValue(jsonEvent, Map.class);
            Long productId = Long.parseLong(eventMap.get("productId").toString());
            storageService.deleteStorage(productId);
        }catch (Exception e){
            e.printStackTrace(); // log later
        }
    }

    @KafkaListener(topics = RESPONSE_TOPIC, groupId = "storage-group")
    public void handleProductResponse(String jsonEvent){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            ListOfProductsRequest request = objectMapper.readValue(jsonEvent, ListOfProductsRequest.class);

        }catch (Exception e){
            e.printStackTrace(); // log later
        }
    }
}
