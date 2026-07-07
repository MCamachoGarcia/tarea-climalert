package ar.edu.utn.ba.ddsi.tarea_climalert.clients.impl;

import ar.edu.utn.ba.ddsi.tarea_climalert.config.WeatherProperties;
import ar.edu.utn.ba.ddsi.tarea_climalert.dtos.ClimaResponse;
import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class WeatherAPIClient {

  private final RestTemplate restTemplate;
  private final WeatherProperties properties;

  public WeatherAPIClient(RestTemplate restTemplate, WeatherProperties properties) {
    this.restTemplate = restTemplate;
    this.properties = properties;
  }

  public ClimaResponse getClima() {
    URI uri = UriComponentsBuilder
        .fromUriString(properties.getBaseUrl())
        .path("/current.json")
        .queryParam("key", properties.getKey())
        .queryParam("q", properties.getUbicacion())
        .build()
        .toUri();

    return restTemplate.getForObject(
        uri,
        ClimaResponse.class
    );
  }

}
