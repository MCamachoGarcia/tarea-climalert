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

  //TODO Esta para probar cada 1 min
  @Scheduled(fixedRate = 60000) //300000 = 5min
  public void obtenerClima() {
    climaService.procesarClima();
  }

}
