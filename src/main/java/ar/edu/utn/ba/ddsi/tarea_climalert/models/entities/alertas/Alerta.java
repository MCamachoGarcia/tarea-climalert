package ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.alertas;

import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.climas.Clima;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Alerta {

  private Long id;
  private TipoAlerta tipo;
  private Clima clima;
  private LocalDateTime fechaDeCreacion;

  public Alerta(TipoAlerta tipo, Clima clima) {
    this.id = null;
    this.tipo = tipo;
    this.clima = clima;
    this.fechaDeCreacion = LocalDateTime.now();
  }
}
