package ar.edu.utn.ba.ddsi.tarea_climalert.controllers;

import ar.edu.utn.ba.ddsi.tarea_climalert.dtos.ClimaResponse;
import ar.edu.utn.ba.ddsi.tarea_climalert.services.ClimaService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/climalert/climas")
public class ClimaController {

  private final ClimaService climaService;

  public ClimaController(ClimaService climaService) {
    this.climaService = climaService;
  }

  @GetMapping
  public List<ClimaResponse> getAll() {
    return climaService.findAll();
  }

  @GetMapping("/{id}")
  public ClimaResponse getById(@PathVariable Long id) {
    return climaService.findById(id);
  }

}
