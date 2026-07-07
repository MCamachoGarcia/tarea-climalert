package ar.edu.utn.ba.ddsi.tarea_climalert.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "weather.api")
@Data
public class WeatherProperties {

  private String baseUrl;
  private String key;
  private String ubicacion;
}
