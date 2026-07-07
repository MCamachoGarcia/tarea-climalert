package ar.edu.utn.ba.ddsi.tarea_climalert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TareaClimalertApplication {

	public static void main(String[] args) {
		SpringApplication.run(TareaClimalertApplication.class, args);
	}

}
