package cm.softwareservices.inventoryservice;

import cm.softwareservices.inventoryservice.model.Inventory;
import cm.softwareservices.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository repository) {
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("IPH-1");
			inventory.setQuantity(1000);

			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("IPH-2");
			inventory1.setQuantity(500);

			repository.save(inventory);
			repository.save(inventory1);
		};
	}
}
