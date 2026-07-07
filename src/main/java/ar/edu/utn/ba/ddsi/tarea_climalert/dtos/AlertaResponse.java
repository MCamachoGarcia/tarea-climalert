package ar.edu.utn.ba.ddsi.tarea_climalert.dtos;

import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.alertas.TipoAlerta;
import java.time.LocalDateTime;

public record AlertaResponse(
    TipoAlerta tipoAlerta,
    Double temperatura,
    int humedad,
    LocalDateTime fechaDeCreacion
) {
}
