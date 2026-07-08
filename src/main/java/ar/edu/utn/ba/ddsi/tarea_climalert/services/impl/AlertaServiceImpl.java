package ar.edu.utn.ba.ddsi.tarea_climalert.services.impl;

import ar.edu.utn.ba.ddsi.tarea_climalert.clients.NotificationAdapter;
import ar.edu.utn.ba.ddsi.tarea_climalert.dtos.AlertaResponse;
import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.alertas.Alerta;
import ar.edu.utn.ba.ddsi.tarea_climalert.alertas.CondicionAlerta;
import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.climas.Clima;
import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.climas.EstadoAnalisis;
import ar.edu.utn.ba.ddsi.tarea_climalert.repositories.AlertaRepository;
import ar.edu.utn.ba.ddsi.tarea_climalert.services.AlertaService;
import ar.edu.utn.ba.ddsi.tarea_climalert.services.ClimaService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AlertaServiceImpl implements AlertaService {

  private final AlertaRepository alertaRepository;
  private final ClimaService climaService;
  private final NotificationAdapter notificationAdapter;
  private final List<CondicionAlerta> condiciones;

  public AlertaServiceImpl(AlertaRepository alertaRepository, ClimaService climaService, NotificationAdapter notificationAdapter, List<CondicionAlerta> condiciones) {
    this.alertaRepository = alertaRepository;
    this.climaService = climaService;
    this.notificationAdapter = notificationAdapter;
    this.condiciones = condiciones;
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

    climaService.findLast().ifPresent(clima -> {

      if (clima.getEstadoAnalisis() == EstadoAnalisis.ANALIZADO) {
        return;
      }

      condiciones.stream()
          .filter(condicion -> condicion.cumple(clima))
          .forEach(condicion -> generarAlerta(condicion, clima));
      clima.setEstadoAnalisis(EstadoAnalisis.ANALIZADO);
      climaService.update(clima);
    });

  }

  private void generarAlerta(CondicionAlerta condicion, Clima clima) {
    Alerta alerta = new Alerta(condicion.tipo(), clima);
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
