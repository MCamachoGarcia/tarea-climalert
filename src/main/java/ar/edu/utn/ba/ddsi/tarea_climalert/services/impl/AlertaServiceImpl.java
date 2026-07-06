package ar.edu.utn.ba.ddsi.tarea_climalert.services.impl;

import ar.edu.utn.ba.ddsi.tarea_climalert.dtos.AlertaResponse;
import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.Alerta;
import ar.edu.utn.ba.ddsi.tarea_climalert.repositories.AlertaRepository;
import ar.edu.utn.ba.ddsi.tarea_climalert.services.AlertaService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AlertaServiceImpl implements AlertaService {

  private final AlertaRepository alertaRepository;

  public AlertaServiceImpl(AlertaRepository alertaRepository) {
    this.alertaRepository = alertaRepository;
  }

  @Override
  public List<AlertaResponse> findAll() {
    return alertaRepository.findAll().stream().map(this::toResponse).toList();
  }

  @Override
  public AlertaResponse findById(Long id) {
    return toResponse(getAlertOrThrow(id));
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
