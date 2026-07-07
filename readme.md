# Climalert

## Alumno

- **Nombre:** Mauricio Sebastian Camacho Garcia
- **Legajo:** 213.293-0
- **Curso:** K3152

## Descripción

Climalert es un servicio desarrollado con Spring Boot que monitorea periódicamente las condiciones climáticas mediante la integración con **WeatherAPI**.

El sistema obtiene información meteorológica de una ubicación fija, almacena un registro histórico y analiza periódicamente los datos para detectar condiciones climáticas peligrosas.

Cuando se detecta una condición crítica, se genera una alerta y se envía una notificación por correo electrónico a los destinatarios configurados.

### Condición de alerta

Se considera una condición climática peligrosa cuando:

- Temperatura mayor a **35 °C**
- Humedad superior al **60 %**

## Funcionalidades

- Integración REST con WeatherAPI.
- Consulta automática del clima cada 5 minutos.
- Almacenamiento del historial de mediciones.
- Análisis automático del último registro cada 1 minuto.
- Generación de alertas.
- Envío automático de correos electrónicos mediante Gmail SMTP.

## Variables de entorno

Para ejecutar el proyecto es necesario configurar las siguientes variables de entorno:

| Variable | Descripción |
|----------|-------------|
| `WEATHER_API_BASE_URL` | URL base de WeatherAPI (ej: `https://api.weatherapi.com/v1`) |
| `WEATHER_API_KEY` | API Key de WeatherAPI |
| `WEATHER_API_UBICACION` | Ubicación a consultar (ej: `CABA`) |
| `MAIL_USERNAME` | Dirección de correo utilizada para enviar las alertas |
| `MAIL_PASSWORD` | Contraseña de aplicación de Gmail |
| `ALERTA_EMAIL_1` | Primer destinatario de las alertas |
| `ALERTA_EMAIL_2` | Segundo destinatario de las alertas |
| `ALERTA_EMAIL_3` | Tercer destinatario de las alertas |

## Configuración

El proyecto utiliza el siguiente archivo `application.yml`:

```yaml
spring:
  application:
    name: tarea-climalert

  mail:
    host: smtp.gmail.com
    port: 587
    username: ${MAIL_USERNAME}
    password: ${MAIL_PASSWORD}

weather:
  api:
    base-url: ${WEATHER_API_BASE_URL}
    key: ${WEATHER_API_KEY}
    ubicacion: ${WEATHER_API_UBICACION}

alertas:
  emails:
    - ${ALERTA_EMAIL_1}
    - ${ALERTA_EMAIL_2}
    - ${ALERTA_EMAIL_3}
```

## Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Scheduling
- Spring Mail
- RestTemplate
- Lombok