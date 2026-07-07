package ar.edu.utn.ba.ddsi.tarea_climalert;

import ar.edu.utn.ba.ddsi.tarea_climalert.config.AlertasProperties;
import ar.edu.utn.ba.ddsi.tarea_climalert.config.WeatherProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({WeatherProperties.class, AlertasProperties.class})
public class TareaClimalertApplication {

	public static void main(String[] args) {
		SpringApplication.run(TareaClimalertApplication.class, args);
	}

}
