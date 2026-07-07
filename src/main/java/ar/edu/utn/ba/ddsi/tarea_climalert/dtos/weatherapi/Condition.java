package ar.edu.utn.ba.ddsi.tarea_climalert.dtos.weatherapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Condition(

    @JsonProperty("text")
    String descripcion

) {
}
