package ar.edu.utn.ba.ddsi.tarea_climalert.alertas.acciones;

import ar.edu.utn.ba.ddsi.tarea_climalert.clients.NotificationAdapter;
import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.alertas.Alerta;
import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.alertas.TipoAlerta;
import org.springframework.stereotype.Component;

@Component
public class EmailAccionAlerta implements AccionAlerta {

  private final NotificationAdapter notificationAdapter;

  public EmailAccionAlerta(NotificationAdapter notificationAdapter) {
    this.notificationAdapter = notificationAdapter;
  }

  @Override
  public boolean soporta(TipoAlerta tipo) {
    return tipo == TipoAlerta.CONDICION_PELIGROSA;
  }

  @Override
  public void ejecutar(Alerta alerta) {
    notificationAdapter.enviarAlerta(alerta);
  }

}
