package ar.edu.utn.ba.ddsi.tarea_climalert.services;

import ar.edu.utn.ba.ddsi.tarea_climalert.dtos.AlertaResponse;
import java.util.List;

public interface AlertaService {

  List<AlertaResponse> findAll();

  AlertaResponse findById(Long id);

  void procesarUltimoClima();

}
