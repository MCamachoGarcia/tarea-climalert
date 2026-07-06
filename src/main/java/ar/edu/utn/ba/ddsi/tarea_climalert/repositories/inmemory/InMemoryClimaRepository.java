package ar.edu.utn.ba.ddsi.tarea_climalert.repositories.inmemory;

import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.Clima;
import ar.edu.utn.ba.ddsi.tarea_climalert.repositories.ClimaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryClimaRepository implements ClimaRepository {

  private final List<Clima> climas = new ArrayList<>();
  //TODO

  @Override
  public List<Clima> findAll() {
    return new ArrayList<>(climas);
  }

  @Override
  public Optional<Clima> findById(Long id) {
    return climas.stream().filter(c -> c.getId().equals(id)).findFirst();
  }

  @Override
  public Clima save(Clima clima) {
    if (clima.getId() == null) {
      clima.setId(null); //TODO
      climas.add(clima);
      return clima;
    }

    delete(clima);
    climas.add(clima);
    return clima;
  }

  @Override
  public void delete(Clima clima) {
    if (clima.getId() == null) {
      return;
    }

    climas.removeIf(c -> c.getId().equals(clima.getId()));
  }

}
