package com.texoit.testtecnico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.texoit.testtecnico.*"})
public class TestTecnicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestTecnicoApplication.class, args);
	}

}
