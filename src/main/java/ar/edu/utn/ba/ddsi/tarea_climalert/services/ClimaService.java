package ar.edu.utn.ba.ddsi.tarea_climalert.services;

import ar.edu.utn.ba.ddsi.tarea_climalert.dtos.ClimaResponse;
import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.Clima;
import java.util.List;
import java.util.Optional;

public interface ClimaService {

  List<ClimaResponse> findAll();

  ClimaResponse findById(Long id);

  Optional<Clima> findLast();

  void procesarClima();

  void update(Clima clima);

}
