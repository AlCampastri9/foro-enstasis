# Foro (Spring Boot 3 + JWT + MySQL)

## Requisitos
- Java 17+
- Maven 4+
- MySQL 8+

## Configuración rápida
1. Crea la base `foro` en MySQL o deja `createDatabaseIfNotExist=true`.
2. Edita `src/main/resources/application.properties` con tu usuario/clave de MySQL y un `jwt.secret` fuerte.
3. Ejecuta:
   ```bash
   mvn spring-boot:run
   ```

## Endpoints
- `POST /login` → devuelve JWT (Body: `{ "username": "admin", "password": "admin" }` por defecto)
- `POST /topicos` (requiere Bearer Token)
- `GET /topicos` (requiere Bearer Token)
- `GET /topicos/{id}` (requiere Bearer Token)
- `PUT /topicos/{id}` (requiere Bearer Token)
- `DELETE /topicos/{id}` (requiere Bearer Token)

## Nota
El usuario por defecto (admin/admin) se crea en `V1__create_users_courses_topics.sql` con contraseña **BCrypt**. Cambia esto en producción.
