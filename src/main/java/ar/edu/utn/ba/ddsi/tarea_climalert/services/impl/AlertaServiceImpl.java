package ar.edu.utn.ba.ddsi.tarea_climalert.services.impl;

import ar.edu.utn.ba.ddsi.tarea_climalert.clients.NotificationAdapter;
import ar.edu.utn.ba.ddsi.tarea_climalert.dtos.AlertaResponse;
import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.Alerta;
import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.Clima;
import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.TipoAlerta;
import ar.edu.utn.ba.ddsi.tarea_climalert.repositories.AlertaRepository;
import ar.edu.utn.ba.ddsi.tarea_climalert.services.AlertaService;
import ar.edu.utn.ba.ddsi.tarea_climalert.services.ClimaService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AlertaServiceImpl implements AlertaService {

  private final AlertaRepository alertaRepository;
  private final ClimaService climaService;
  private final NotificationAdapter notificationAdapter;

  public AlertaServiceImpl(AlertaRepository alertaRepository, ClimaService climaService, NotificationAdapter notificationAdapter) {
    this.alertaRepository = alertaRepository;
    this.climaService = climaService;
    this.notificationAdapter = notificationAdapter;
  }

  @Override
  public List<AlertaResponse> findAll() {
    return alertaRepository.findAll().stream().map(this::toResponse).toList();
  }

  @Override
  public AlertaResponse findById(Long id) {
    return toResponse(getAlertOrThrow(id));
  }

  @Override
  public void procesarUltimoClima() {

    Optional<Clima> ultimo = climaService.findLast();

    if (ultimo.isEmpty()) {
      return;
    }

    Clima ultimoClima = ultimo.get(); //isProcesado no puede aplicarse sobre un Optional<>

    if (ultimoClima.isProcesado()) {
      return;
    }

    if (esCondicionPeligrosa(ultimoClima)) {
      generarAlerta(ultimoClima);
    }

    ultimoClima.setProcesado(true);
    climaService.update(ultimoClima);
  }

  private boolean esCondicionPeligrosa(Clima clima) {
    return clima.getTemperatura() > 1 && clima.getHumedad() > 1;
  }

  private void generarAlerta(Clima clima) {
    Alerta alerta = new Alerta(TipoAlerta.CONDICION_PELIGROSA, clima);
    alertaRepository.save(alerta);
    notificationAdapter.enviarAlerta(alerta);
  }

  private AlertaResponse toResponse(Alerta alerta) {
    return new AlertaResponse(
        alerta.getTipo(),
        alerta.getClima().getTemperatura(),
        alerta.getClima().getHumedad(),
        alerta.getFechaDeCreacion()
    ); //TODO: se puede mejorar
  }

  private Alerta getAlertOrThrow(Long alertId) {
    return alertaRepository.findById(alertId).
        orElseThrow(() -> new RuntimeException("No se encontro alerta con id " + alertId));
  }

}
