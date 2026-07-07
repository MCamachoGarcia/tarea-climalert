package ar.edu.utn.ba.ddsi.tarea_climalert.dtos.weatherapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CurrentResponse(

    @JsonProperty("condition")
    Condition condicionClimatica,

    @JsonProperty("temp_c")
    Double temperatura,

    @JsonProperty("humidity")
    int humedad
) {
}
