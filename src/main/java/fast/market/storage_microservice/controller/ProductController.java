package fast.market.storage_microservice.controller;

import fast.market.storage_microservice.kafka.producer.ProductKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ProductController {
    @Autowired
    private ProductKafkaProducer productKafkaProducer;

    @PostMapping("/sendProductQuantities")
    public ResponseEntity<String> sendProductQuantities(@RequestBody Map<Long, Integer> productQuantities){
        productKafkaProducer.sendProductQuantities(productQuantities);
        return new ResponseEntity<>("Product quantities sent!", HttpStatus.OK);
    }
}
