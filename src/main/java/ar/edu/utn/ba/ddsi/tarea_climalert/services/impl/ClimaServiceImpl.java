package ar.edu.utn.ba.ddsi.tarea_climalert.services.impl;

import ar.edu.utn.ba.ddsi.tarea_climalert.clients.WeatherAdapter;
import ar.edu.utn.ba.ddsi.tarea_climalert.dtos.ClimaResponse;
import ar.edu.utn.ba.ddsi.tarea_climalert.dtos.CurrentResponse;
import ar.edu.utn.ba.ddsi.tarea_climalert.dtos.LocationResponse;
import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.Clima;
import ar.edu.utn.ba.ddsi.tarea_climalert.repositories.ClimaRepository;
import ar.edu.utn.ba.ddsi.tarea_climalert.services.ClimaService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClimaServiceImpl implements ClimaService {

  private final ClimaRepository climaRepository;
  private final WeatherAdapter weatherProvider;

  public ClimaServiceImpl(ClimaRepository climaRepository, WeatherAdapter weatherProvider) {
    this.climaRepository = climaRepository;
    this.weatherProvider = weatherProvider;
  }

  @Override
  public List<ClimaResponse> findAll() {
    return climaRepository.findAll().stream().map(this::toResponse).toList();
  }

  @Override
  public ClimaResponse findById(Long id) {
    return toResponse(getClimaOrThrow(id));
  }

  @Override
  public void procesarClima() {
    Clima clima = weatherProvider.getClimaActual();

    log.info("Obtenido Clima - Temperatura: {} , Humedad: {}",
        clima.getTemperatura(),
        clima.getHumedad());

    climaRepository.save(clima);
  }

  private ClimaResponse toResponse(Clima clima) {

    LocationResponse location = new LocationResponse(clima.getPais(), clima.getCiudad(), clima.getRegion());
    CurrentResponse current = new CurrentResponse(clima.getTemperatura(), clima.getHumedad());

    return new ClimaResponse(location, current);
  }

  private Clima getClimaOrThrow(Long climaId) {
    return climaRepository.findById(climaId).
        orElseThrow(() -> new RuntimeException("No se encontro clima con id " + climaId));
  }

}
