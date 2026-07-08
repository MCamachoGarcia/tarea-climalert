package ar.edu.utn.ba.ddsi.tarea_climalert.services.impl;

import ar.edu.utn.ba.ddsi.tarea_climalert.alertas.acciones.AccionAlerta;
import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.alertas.Alerta;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EjecutadorAccionesAlerta {

  private final List<AccionAlerta> acciones;

  public EjecutadorAccionesAlerta(List<AccionAlerta> acciones) {
    this.acciones = acciones;
  }

  public void ejecutar(Alerta alerta) {
    acciones.stream()
        .filter(accion -> accion.soporta(alerta.getTipo()))
        .forEach(accion -> accion.ejecutar(alerta));
  }

}
