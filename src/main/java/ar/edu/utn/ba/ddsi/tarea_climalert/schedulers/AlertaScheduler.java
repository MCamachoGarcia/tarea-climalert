package ar.edu.utn.ba.ddsi.tarea_climalert.schedulers;

import ar.edu.utn.ba.ddsi.tarea_climalert.services.AlertaService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AlertaScheduler {

  private final AlertaService alertaService;

  public AlertaScheduler(AlertaService alertaService) {
    this.alertaService = alertaService;
  }

  @Scheduled(fixedRate = 60000)
  public void procesarDatos() {
    alertaService.procesarUltimoClima();
  }
}
