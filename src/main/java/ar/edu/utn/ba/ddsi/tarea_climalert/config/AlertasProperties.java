package ar.edu.utn.ba.ddsi.tarea_climalert.config;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "alertas")
@Data
public class AlertasProperties {

  private String senderEmail;
  private List<String> emails;
}
