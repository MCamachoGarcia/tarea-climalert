package ar.edu.utn.ba.ddsi.tarea_climalert.models.entities;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Clima {

  private Long id; // Para trazabilidad en memoria
  private String pais;
  private String ciudad;
  private String region;
  private Double temperatura;
  private int humedad;
  private LocalDateTime timestamp;

  public Clima(String pais, String ciudad, String region, Double temperatura, int humedad) {
    this.id = null; //TODO
    this.pais = pais;
    this.ciudad = ciudad;
    this.region = region;
    this.temperatura = temperatura;
    this.humedad = humedad;
    this.timestamp = LocalDateTime.now();
  }

}
