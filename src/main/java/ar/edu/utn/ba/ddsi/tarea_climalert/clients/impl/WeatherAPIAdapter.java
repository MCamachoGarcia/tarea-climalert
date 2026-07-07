package ar.edu.utn.ba.ddsi.tarea_climalert.clients.impl;

import ar.edu.utn.ba.ddsi.tarea_climalert.clients.WeatherAdapter;
import ar.edu.utn.ba.ddsi.tarea_climalert.dtos.weatherapi.ClimaResponse;
import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.climas.Clima;
import org.springframework.stereotype.Component;

@Component
public class WeatherAPIAdapter implements WeatherAdapter {

  private final WeatherAPIClient client;

  public WeatherAPIAdapter(WeatherAPIClient client) {
    this.client = client;
  }

  @Override
  public Clima getClimaActual() {
    ClimaResponse response = client.getClima();

    return new Clima(
        response.location().pais(),
        response.location().ciudad(),
        response.location().region(),
        response.current().temperatura(),
        response.current().humedad(),
        response.current().condicionClimatica().descripcion()
    );
  }

}
