package ar.edu.utn.ba.ddsi.tarea_climalert.dtos.weatherapi;

public record ClimaResponse(
    LocationResponse location,
    CurrentResponse current
) {
}
