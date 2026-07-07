package ar.edu.utn.ba.ddsi.tarea_climalert.services.impl;

import ar.edu.utn.ba.ddsi.tarea_climalert.clients.WeatherAdapter;
import ar.edu.utn.ba.ddsi.tarea_climalert.dtos.ClimaResponseOwn;
import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.climas.Clima;
import ar.edu.utn.ba.ddsi.tarea_climalert.repositories.ClimaRepository;
import ar.edu.utn.ba.ddsi.tarea_climalert.services.ClimaService;
import java.util.List;
import java.util.Optional;
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
  public List<ClimaResponseOwn> findAll() {
    return climaRepository.findAll().stream().map(this::toResponse).toList();
  }

  @Override
  public ClimaResponseOwn findById(Long id) {
    return toResponse(getClimaOrThrow(id));
  }

  @Override
  public Optional<Clima> findLast() {
    return climaRepository.findUltimo();
  }

  @Override
  public void procesarClima() {
    Clima clima = weatherProvider.getClimaActual();

    log.info("Obtenido Clima - Condicion: {} , Temperatura: {} , Humedad: {} , Timestamp: {}",
        clima.getDescripcion(),
        clima.getTemperatura(),
        clima.getHumedad(),
        clima.getTimestamp());

    climaRepository.save(clima);
  }

  @Override
  public void update(Clima clima) {
    climaRepository.save(clima);
  }

  private ClimaResponseOwn toResponse(Clima clima) {
    return new ClimaResponseOwn(
        clima.getPais(),
        clima.getCiudad(),
        clima.getRegion(),
        clima.getDescripcion(),
        clima.getTemperatura(),
        clima.getHumedad(),
        clima.getTimestamp()
    );
  }

  private Clima getClimaOrThrow(Long climaId) {
    return climaRepository.findById(climaId).
        orElseThrow(() -> new RuntimeException("No se encontro clima con id " + climaId));
  }

}
