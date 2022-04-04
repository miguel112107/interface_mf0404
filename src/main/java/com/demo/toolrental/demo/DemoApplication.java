package com.demo.toolrental.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.demo.toolrental.demo.services.CheckoutService;
import com.demo.toolrental.demo.models.RentalAgreement;
import java.time.LocalDate;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws Exception{
		SpringApplication.run(DemoApplication.class, args);
	}

}
