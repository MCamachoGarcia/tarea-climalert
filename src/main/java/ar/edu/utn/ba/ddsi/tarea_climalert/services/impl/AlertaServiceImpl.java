package ar.edu.utn.ba.ddsi.tarea_climalert.services.impl;

import ar.edu.utn.ba.ddsi.tarea_climalert.dtos.AlertaResponse;
import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.alertas.Alerta;
import ar.edu.utn.ba.ddsi.tarea_climalert.alertas.condiciones.CondicionAlerta;
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
  private final List<CondicionAlerta> condiciones;
  private final EjecutadorAccionesAlerta ejecutadorAcciones;

  public AlertaServiceImpl(AlertaRepository alertaRepository, ClimaService climaService, List<CondicionAlerta> condiciones, EjecutadorAccionesAlerta ejecutadorAcciones) {
    this.alertaRepository = alertaRepository;
    this.climaService = climaService;
    this.condiciones = condiciones;
    this.ejecutadorAcciones = ejecutadorAcciones;
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
          .map(condicion -> new Alerta(condicion.tipo(), clima))
          .forEach(this::generarAlerta);
      
      clima.setEstadoAnalisis(EstadoAnalisis.ANALIZADO);
      climaService.update(clima);
    });

  }

  private void generarAlerta(Alerta alerta) {
    log.info("Alerta generada - {} , Temperatura: {} °C, Humedad: {} %, Timestamp: {}",
        alerta.getTipo(),
        alerta.getClima().getTemperatura(),
        alerta.getClima().getHumedad(),
        alerta.getFechaDeCreacion());
    alertaRepository.save(alerta);
    ejecutadorAcciones.ejecutar(alerta);
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
