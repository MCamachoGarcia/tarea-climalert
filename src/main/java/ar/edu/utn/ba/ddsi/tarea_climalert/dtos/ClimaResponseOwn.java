package ar.edu.utn.ba.ddsi.tarea_climalert.dtos;

import java.time.LocalDateTime;

public record ClimaResponseOwn(
    String pais,
    String ciudad,
    String region,
    String condicionClimatica,
    Double temperatura,
    int humedad,
    LocalDateTime timestamp
) {
}
