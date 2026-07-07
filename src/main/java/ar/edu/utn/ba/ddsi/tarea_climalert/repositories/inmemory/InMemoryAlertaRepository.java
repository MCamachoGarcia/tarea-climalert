package ar.edu.utn.ba.ddsi.tarea_climalert.repositories.inmemory;

import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.alertas.Alerta;
import ar.edu.utn.ba.ddsi.tarea_climalert.repositories.AlertaRepository;
import ar.edu.utn.ba.ddsi.tarea_climalert.utils.GeneradorIdSecuencial;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryAlertaRepository implements AlertaRepository {

  private final List<Alerta> alertas = new ArrayList<>();
  private final GeneradorIdSecuencial generadorId = new GeneradorIdSecuencial();

  @Override
  public List<Alerta> findAll() {
    return new ArrayList<>(alertas);
  }

  @Override
  public Optional<Alerta> findById(Long id) {
    return alertas.stream().filter(a -> a.getId().equals(id)).findFirst();
  }

  @Override
  public Alerta save(Alerta alerta) {
    if (alerta.getId() == null) {
      alerta.setId(generadorId.siguiente());
      alertas.add(alerta);
      return alerta;
    }

    delete(alerta);
    alertas.add(alerta);
    return alerta;
  }

  @Override
  public void delete(Alerta alerta) {
    if (alerta.getId() == null) {
      return;
    }

    alertas.removeIf(a -> a.getId().equals(alerta.getId()));
  }

}
