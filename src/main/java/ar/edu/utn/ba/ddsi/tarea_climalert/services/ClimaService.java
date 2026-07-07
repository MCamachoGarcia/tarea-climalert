package ar.edu.utn.ba.ddsi.tarea_climalert.services;

import ar.edu.utn.ba.ddsi.tarea_climalert.dtos.ClimaResponse;
import java.util.List;

public interface ClimaService {

  List<ClimaResponse> findAll();

  ClimaResponse findById(Long id);

  void procesarClima();

}
