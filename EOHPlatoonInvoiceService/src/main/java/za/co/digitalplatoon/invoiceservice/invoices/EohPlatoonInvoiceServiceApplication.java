package za.co.digitalplatoon.invoiceservice.invoices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
//@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories
@SpringBootApplication
public class EohPlatoonInvoiceServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(EohPlatoonInvoiceServiceApplication.class, args);
	}
	
}
