package ar.edu.utn.ba.ddsi.tarea_climalert.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CurrentResponse(

    @JsonProperty("temp_c")
    Double temeperatura,

    @JsonProperty("humidity")
    int humedad

) {
}
