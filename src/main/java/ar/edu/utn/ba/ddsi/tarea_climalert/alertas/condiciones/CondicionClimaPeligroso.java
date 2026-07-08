package ar.edu.utn.ba.ddsi.tarea_climalert.alertas.condiciones;

import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.alertas.TipoAlerta;
import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.climas.Clima;
import org.springframework.stereotype.Component;

@Component
public class CondicionClimaPeligroso implements CondicionAlerta {

  @Override
  public boolean cumple(Clima clima) {
    return clima.getTemperatura() > 35 && clima.getHumedad() > 60;
  }

  @Override
  public TipoAlerta tipo() {
    return TipoAlerta.CONDICION_PELIGROSA;
  }

}
