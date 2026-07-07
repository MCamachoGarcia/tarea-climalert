package ar.edu.utn.ba.ddsi.tarea_climalert.services;

import ar.edu.utn.ba.ddsi.tarea_climalert.dtos.ClimaResponseOwn;
import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.climas.Clima;
import java.util.List;
import java.util.Optional;

public interface ClimaService {

  List<ClimaResponseOwn> findAll();

  ClimaResponseOwn findById(Long id);

  Optional<Clima> findLast();

  void procesarClima();

  void update(Clima clima);

}
