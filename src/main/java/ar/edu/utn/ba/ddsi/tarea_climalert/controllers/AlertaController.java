package ar.edu.utn.ba.ddsi.tarea_climalert.controllers;

import ar.edu.utn.ba.ddsi.tarea_climalert.dtos.AlertaResponse;
import ar.edu.utn.ba.ddsi.tarea_climalert.services.AlertaService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/climalert/alertas")
public class AlertaController {

  private final AlertaService alertaService;

  public AlertaController(AlertaService alertaService) {
    this.alertaService = alertaService;
  }

  @GetMapping
  public List<AlertaResponse> getAll() {
    return alertaService.findAll();
  }

  @GetMapping("/{id}")
  public AlertaResponse getById(@PathVariable Long id) {
    return alertaService.findById(id);
  }

}
