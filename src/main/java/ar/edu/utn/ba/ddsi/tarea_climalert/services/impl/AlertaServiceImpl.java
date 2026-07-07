package ar.edu.utn.ba.ddsi.tarea_climalert.services.impl;

import ar.edu.utn.ba.ddsi.tarea_climalert.clients.NotificationAdapter;
import ar.edu.utn.ba.ddsi.tarea_climalert.dtos.AlertaResponse;
import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.alertas.Alerta;
import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.climas.Clima;
import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.climas.EstadoAnalisis;
import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.alertas.TipoAlerta;
import ar.edu.utn.ba.ddsi.tarea_climalert.repositories.AlertaRepository;
import ar.edu.utn.ba.ddsi.tarea_climalert.services.AlertaService;
import ar.edu.utn.ba.ddsi.tarea_climalert.services.ClimaService;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
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

    Clima ultimoClima = ultimo.get(); //getEstadoAnalisis no puede aplicarse sobre un Optional<>

    if (ultimoClima.getEstadoAnalisis() == EstadoAnalisis.ANALIZADO) {
      return;
    }

    if (esCondicionPeligrosa(ultimoClima)) {
      generarAlerta(ultimoClima);
    }

    ultimoClima.setEstadoAnalisis(EstadoAnalisis.ANALIZADO);
    climaService.update(ultimoClima);
  }

  private boolean esCondicionPeligrosa(Clima clima) {
    return clima.getTemperatura() > 35 && clima.getHumedad() > 60;
  }

  private void generarAlerta(Clima clima) {
    Alerta alerta = new Alerta(TipoAlerta.CONDICION_PELIGROSA, clima);
    log.info("Alerta generada - {} , Temperatura: {} °C, Humedad: {} %, Timestamp: {}",
        alerta.getTipo(),
        alerta.getClima().getTemperatura(),
        alerta.getClima().getHumedad(),
        alerta.getFechaDeCreacion());
    alertaRepository.save(alerta);
    notificationAdapter.enviarAlerta(alerta);
  }

  private AlertaResponse toResponse(Alerta alerta) {
    return new AlertaResponse(
        alerta.getTipo(),
        alerta.getClima().getTemperatura(),
        alerta.getClima().getHumedad(),
        alerta.getFechaDeCreacion()
    );
  }

  private Alerta getAlertOrThrow(Long alertId) {
    return alertaRepository.findById(alertId).
        orElseThrow(() -> new RuntimeException("No se encontro alerta con id " + alertId));
  }

}
