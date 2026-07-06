package ar.edu.utn.ba.ddsi.tarea_climalert.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LocationResponse(

    @JsonProperty("country")
    String pais,

    @JsonProperty("name")
    String ciudad,

    String region
) {
}
