package ar.edu.utn.ba.ddsi.tarea_climalert.clients.impl;

import ar.edu.utn.ba.ddsi.tarea_climalert.clients.NotificationAdapter;
import ar.edu.utn.ba.ddsi.tarea_climalert.models.entities.Alerta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationAdapter implements NotificationAdapter {

  @Value("${MAIL_USERNAME}")
  private String senderEmail;

  @Value("${ALERTA_EMAIL_1}")
  private String receiverEmail;

  private final JavaMailSender mailSender;

  public EmailNotificationAdapter(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Override
  public void enviarAlerta(Alerta alerta) {

    SimpleMailMessage mensaje = new SimpleMailMessage();

    mensaje.setFrom(senderEmail);
    //TODO
    //varios emails en el properties
    mensaje.setTo(receiverEmail);
    mensaje.setSubject("⚠ Alerta Climática");
    mensaje.setText("""
                Se detectó una condición climática peligrosa.

                País: %s
                Ciudad: %s
                Región: %s

                Temperatura: %.1f °C
                Humedad: %d %%
                Fecha: %s
                """.formatted(
        alerta.getClima().getPais(),
        alerta.getClima().getCiudad(),
        alerta.getClima().getRegion(),
        alerta.getClima().getTemperatura(),
        alerta.getClima().getHumedad(),
        alerta.getClima().getTimestamp()
    ));

    mailSender.send(mensaje);
  }

}
