package fast.market.storage_microservice.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fast.market.storage_microservice.events.ListOfProductsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class StorageKafkaProducer {
    @Autowired
    private KafkaTemplate<String, ListOfProductsRequest> kafkaTemplate;
    private static final String REQUEST_TOPIC = "products-for-storage";
    private static final String RESPONSE_TOPIC = "products-response";

    public void requestProducts(ListOfProductsRequest event){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            String jsonEvent = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(REQUEST_TOPIC, event);
        }catch(JsonProcessingException e){
            e.printStackTrace(); // log later
        }
    }

    @KafkaListener(topics = RESPONSE_TOPIC, groupId = "storage-group")
    public void handleProductResponse(String jsonEvent){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            ListOfProductsRequest event = objectMapper.readValue(jsonEvent, ListOfProductsRequest.class);
            kafkaTemplate.send(RESPONSE_TOPIC, event);
        }catch(JsonProcessingException e){
            e.printStackTrace(); // log later
        }
    }
}
