package ar.edu.utn.ba.ddsi.tarea_climalert.dtos;

public record ClimaResponse(
    LocationResponse location,
    CurrentResponse current
) {
}
