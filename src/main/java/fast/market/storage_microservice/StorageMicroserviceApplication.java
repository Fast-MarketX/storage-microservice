package fast.market.storage_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class StorageMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StorageMicroserviceApplication.class, args);
	}

}
