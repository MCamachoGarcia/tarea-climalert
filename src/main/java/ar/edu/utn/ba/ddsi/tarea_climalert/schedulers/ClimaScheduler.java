package ar.edu.utn.ba.ddsi.tarea_climalert.schedulers;

import ar.edu.utn.ba.ddsi.tarea_climalert.services.ClimaService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClimaScheduler {

  private final ClimaService climaService;
  //private weather

  public ClimaScheduler(ClimaService climaService) {
    this.climaService = climaService;
  }

  @Scheduled(fixedRate = 30000) //300000 = 5min
  public void obtenerClima() {
    climaService.procesarClima();
  }

}
