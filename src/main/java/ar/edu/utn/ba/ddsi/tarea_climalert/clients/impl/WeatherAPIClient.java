package ar.edu.utn.ba.ddsi.tarea_climalert.clients.impl;

import ar.edu.utn.ba.ddsi.tarea_climalert.dtos.ClimaResponse;
import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class WeatherAPIClient {

  private final RestTemplate restTemplate;
  //private final WeatherProperties properties;
  //TODO

  @Value("${WEATHER_API_BASE_URL}")
  private String baseUrl;

  @Value("${WEATHER_API_KEY}")
  private String apiKey;

  @Value("${WEATHER_API_UBICACION}")
  private String ubicacion;


  public WeatherAPIClient(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
    //this.properties = properties;
  }

  public ClimaResponse getClima() {
    URI uri = UriComponentsBuilder
        .fromUriString(baseUrl)
        .path("/current.json")
        .queryParam("key", apiKey)
        .queryParam("q", ubicacion)
        .build()
        .toUri();

    return restTemplate.getForObject(
        uri,
        ClimaResponse.class
    );
  }

}
