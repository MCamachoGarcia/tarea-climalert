package ar.edu.utn.ba.ddsi.tarea_climalert.repositories;

import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.Alerta;
import java.util.List;
import java.util.Optional;

public interface AlertaRepository {

  List<Alerta> findAll();

  Optional<Alerta> findById(Long id);

  Alerta save(Alerta alerta);

  void delete(Alerta alerta);

}
