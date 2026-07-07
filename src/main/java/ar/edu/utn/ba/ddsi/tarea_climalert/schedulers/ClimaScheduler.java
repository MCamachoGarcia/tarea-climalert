package ar.edu.utn.ba.ddsi.tarea_climalert.schedulers;

import ar.edu.utn.ba.ddsi.tarea_climalert.services.ClimaService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClimaScheduler {

  private final ClimaService climaService;

  public ClimaScheduler(ClimaService climaService) {
    this.climaService = climaService;
  }

  @Scheduled(fixedRate = 300000)
  public void obtenerClima() {
    climaService.procesarClima();
  }

}
