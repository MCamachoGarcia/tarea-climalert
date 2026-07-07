package ar.edu.utn.ba.ddsi.tarea_climalert.clients;

import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.Alerta;

public interface NotificationAdapter {

  void enviarAlerta(Alerta alerta);

}
