package ar.edu.utn.ba.ddsi.tarea_climalert.dtos;

import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.TipoAlerta;
import java.time.LocalDateTime;

public record AlertaResponse(
    TipoAlerta tipoAlerta,
    Double temperatura,
    int humedad,
    //TipoAlerta:  clima podria ser un dto incluido, ya que podrian haber diferentes tipos de alerta
    LocalDateTime fechaDeCreacion
) {
}
