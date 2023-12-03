package kz.iitu.javaLabs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling//TODO check
public class JavaLabsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaLabsApplication.class, args);
	}

}
