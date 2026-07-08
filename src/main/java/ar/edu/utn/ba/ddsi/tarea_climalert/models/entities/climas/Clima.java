package ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.climas;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Clima {

  private Long id;
  private String pais;
  private String ciudad;
  private String region;
  private Double temperatura;
  private int humedad;
  private String descripcion;
  private LocalDateTime timestamp;
  private EstadoAnalisis estadoAnalisis;

  public Clima(String pais, String ciudad, String region, Double temperatura, int humedad, String descripcion) {
    this.id = null;
    this.pais = pais;
    this.ciudad = ciudad;
    this.region = region;
    this.temperatura = temperatura;
    this.humedad = humedad;
    this.descripcion = descripcion;
    this.timestamp = LocalDateTime.now();
    this.estadoAnalisis = EstadoAnalisis.PENDIENTE;
  }

}
