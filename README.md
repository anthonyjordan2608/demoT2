// Proyecto DemoT2 - Sistema de Alquiler de Películas

Este proyecto es una aplicación web para la gestión de alquileres de películas, desarrollada con Spring Boot, Thymeleaf y PostgreSQL (usando Supabase como servicio de base de datos).

// Características Empleadas

* Registro de nuevos alquileres de películas.
* Selección de cliente y estado del alquiler.
* Visualización de películas disponibles, género y stock.
* Control de stock de películas al realizar un alquiler.
* Gestión de clientes y películas desde la base de datos.

// Tecnologías utilizadas

- Java 17+ (compatibilidad con JDK)
- Spring Boot 3.x
- Thymeleaf (para la vista)
- PostgreSQL (Implementacion de Supabase)
- Bootstrap 5 (para algunos estilos)

// Estructura principal

- /src/main/java/com/blockbusterfake/demoT2/model**: Entidades JPA (`Alquiler`, `Cliente`, `DetalleAlquiler`,           `DetalleAlquilerId`, `Estado`, `Pelicula` )
- /src/main/java/com/blockbusterfake/demoT2/controller**: Controladores Spring MVC
- /src/main/java/com/blockbusterfake/demoT2/service**: Lógica de negocio y servicios
- /src/main/resources/templates/alquiler/formulario.html**: Vista principal para registrar alquileres

// Configuración

1. Base de datos 
   - Crea tu base de datos en Supabase.
   - Usa el siguiente script para crear las tablas y algunos datos:

   ```sql
   CREATE TABLE IF NOT EXISTS clientes (
     id_cliente SERIAL PRIMARY KEY,
     nombre VARCHAR(100) NOT NULL,
     email VARCHAR(100) NOT NULL UNIQUE
   );

   CREATE TABLE IF NOT EXISTS peliculas (
     id_pelicula SERIAL PRIMARY KEY,
     titulo VARCHAR(150) NOT NULL,
     genero VARCHAR(60),
     stock INT NOT NULL DEFAULT 0
   );

   CREATE TYPE estado_alquiler AS ENUM ('ACTIVO', 'DEVUELTO', 'RETRASADO');

   CREATE TABLE IF NOT EXISTS alquileres (
     id_alquiler SERIAL PRIMARY KEY,
     fecha TIMESTAMP NOT NULL,
     id_cliente INT NOT NULL,
     total DECIMAL(8,2) NOT NULL,
     estado estado_alquiler NOT NULL DEFAULT 'ACTIVO',
     FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
   );

   CREATE TABLE IF NOT EXISTS detalle_alquiler (
     id_alquiler INT NOT NULL,
     id_pelicula INT NOT NULL,
     cantidad INT NOT NULL,
     PRIMARY KEY (id_alquiler, id_pelicula),
     FOREIGN KEY (id_alquiler) REFERENCES alquileres(id_alquiler),
     FOREIGN KEY (id_pelicula) REFERENCES peliculas(id_pelicula)
   );
   ```

   - Inserta algunos datos de ejemplo:

   ```sql
   INSERT INTO clientes (nombre, email) VALUES
     ('Anthony Perez', 'anthony_jordan@gmail.com'),

   INSERT INTO peliculas (titulo, genero, stock) VALUES
     ('The Avengers', 'Ciencia Ficción', 10),
     ('Bohemian Rhapsody', 'Musical', 5),
     ('La vida es bella', 'Drama', 7);
   ```

2. Configura `application.properties`
   Ajusta la conexión a tu base de datos en `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:postgresql://<host>:<port>/<database>
   spring.datasource.username=<usuario>
   spring.datasource.password=<contraseña>
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   server.port=8081
   ```

3. Ejecuta la aplicación 
   - Desde VS Code o con Maven:
     ```
     mvn spring-boot:run
     ```

4. Accede a la aplicación
   - Abre tu navegador en:  
     [http://localhost:8081/alquileres/nuevo](http://localhost:8081/alquileres/nuevo)

// Uso

- Selecciona un cliente y el estado del alquiler.
- Ingresa la cantidad de cada película a alquilar (no puede superar el stock).
- Haz clic en "Procesar Alquiler" para registrar el alquiler y actualizar el stock.
- Registrado exitosamente. 

// Notas

- Puedes agregar más clientes y películas directamente desde Supabase usando sentencias SQL.
- El stock de las películas se actualiza automáticamente al realizar un alquiler.
- El sistema es solo para fines educativos y puede ser extendido según tus necesidades.

---

// Autor:
Anthony Jordan Pérez Rodriguez 
Codigo de su amigable vecino Spider-Man XD --Your Friendly Neighborhood Spider-Man jeje
Programador mitad de la mitad de la mitad de junior xD
Cibertec - Lenguaje de Programación 2