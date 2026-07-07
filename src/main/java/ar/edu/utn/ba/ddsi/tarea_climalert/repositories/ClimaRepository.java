package ar.edu.utn.ba.ddsi.tarea_climalert.repositories;

import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.climas.Clima;
import java.util.List;
import java.util.Optional;

public interface ClimaRepository {

  List<Clima> findAll();

  Optional<Clima> findById(Long id);

  Optional<Clima> findUltimo();

  Clima save(Clima clima);

  void delete(Clima clima);

}