package ar.edu.utn.ba.ddsi.tarea_climalert.alertas.condiciones;

import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.alertas.TipoAlerta;
import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.climas.Clima;

public interface CondicionAlerta {

  boolean cumple(Clima clima);

  TipoAlerta tipo();

}
