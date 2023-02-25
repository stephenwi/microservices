package cm.services.softwareservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SoftwareServicesApplication {
	public static void main(String[] args) {
		SpringApplication.run(SoftwareServicesApplication.class, args);
	}

}
