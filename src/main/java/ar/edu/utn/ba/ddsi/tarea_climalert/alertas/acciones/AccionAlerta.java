package ar.edu.utn.ba.ddsi.tarea_climalert.alertas.acciones;

import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.alertas.Alerta;
import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.alertas.TipoAlerta;

public interface AccionAlerta {

  boolean soporta(TipoAlerta tipo);

  void ejecutar(Alerta alerta);

}
