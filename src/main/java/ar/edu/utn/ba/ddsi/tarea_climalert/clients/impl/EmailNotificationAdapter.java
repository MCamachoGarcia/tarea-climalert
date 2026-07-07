package ar.edu.utn.ba.ddsi.tarea_climalert.clients.impl;

import ar.edu.utn.ba.ddsi.tarea_climalert.clients.NotificationAdapter;
import ar.edu.utn.ba.ddsi.tarea_climalert.config.AlertasProperties;
import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.alertas.Alerta;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationAdapter implements NotificationAdapter {

  private final JavaMailSender mailSender;
  private final AlertasProperties properties;

  public EmailNotificationAdapter(JavaMailSender mailSender, AlertasProperties alertasProperties) {
    this.mailSender = mailSender;
    this.properties = alertasProperties;
  }

  @Override
  public void enviarAlerta(Alerta alerta) {

    SimpleMailMessage mensaje = new SimpleMailMessage();

    mensaje.setFrom(properties.getSenderEmail());
    mensaje.setTo(properties.getEmails().toArray(String[]::new));
    mensaje.setSubject("⚠ Alerta Climática");
    mensaje.setText("""
                Se detectó una condición climática peligrosa.

                País: %s
                Ciudad: %s
                Región: %s
                
                Condición: %s

                Temperatura: %.1f °C
                Humedad: %d %%
                Fecha: %s
                """.formatted(
        alerta.getClima().getPais(),
        alerta.getClima().getCiudad(),
        alerta.getClima().getRegion(),
        alerta.getClima().getDescripcion(),
        alerta.getClima().getTemperatura(),
        alerta.getClima().getHumedad(),
        alerta.getClima().getTimestamp()
    ));

    mailSender.send(mensaje);
  }

}
