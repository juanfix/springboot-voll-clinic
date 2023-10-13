# Voll Clinic

## Descripción

Voll Clinic es un caso de estudio desarrollado durante el bootcamp de backend de Alura Y Oracle. consiste en la gestión de pacientes y doctores de una clínica, en donde se evidencia un CRUD para cada entidad y además se podrá agendar una cita a un paciente con un doctor en una determinada fecha. Cabe mencionar que se incluyen algunas reglas de negocio como validaciones del sistema.

- Solo se debe agendar una cita con un doctor y un paciente en estado activo en el sistema.
- Al agendar una cita, esta debe programarse con al menos 30 minutos de anticipación.
- Solo se puede cancelar una cita ya agendada con mas de 24 horas de anticipación.
- La clínica solo opera desde las 7:00 horas hasta las 19:00 horas.
- No se puede agendar una cita con un doctor el cual ya tenga asignado otro paciente en la misma fecha.
- Un paciente no puede ser agendado en una cita dos veces en una misma fecha.

## Documentación

Pues ver la documentación del proyecto [aquí](src/main/java/com/example/vollclinic/docs/APIDocumentation.pdf).

Como alternativa, puedes usar el siguiente enlace cuando la aplicación esta en ejecución: http://localhost:8080/docs

## Servidor de desarrollo

1. Clonar el repositorio.
2. Abrir la carpeta del proyecto con algún IDE (preferible IntelliJ IDEA)
3. Use el plugin de Spring Boot Maven: mvn spring-boot:run
4. Use el siguiente enlace: http://localhost:8080/