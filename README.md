# Star Wars Movies

### Requisitos Previos
Antes de ejecutar la aplicación, asegúrate de tener instalado lo siguiente:

- Java Development Kit (JDK) 17 o superior
- Una base de datos MariaDB o MySQL (u otro motor de base de datos compatible)
- Postman (para realizar solicitudes HTTP)

### Configuración de la Base de Datos
1. Crea una base de datos en tu sistema de gestión de bases de datos (por ejemplo, MariaDB) con el nombre starwars.
Si deseas usar una base de datos MySQL deberás cambiar el datasource.url en el archivo application.properties por la siguiente: ``spring.datasource.url=jdbc:mysql://localhost:3306/starwars`` y cambiar el datasource.driver por el siguiente: ``spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver``
2. Abre el archivo src/main/resources/application.properties y configura las propiedades de la base de datos como el nombre de usuario y la contraseña de tu base de datos.
```yaml
spring.datasource.url=jdbc:mysql://localhost:3306/examen_estudiante_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

```

### Ejecución de la Aplicación
Para ejecutar la aplicación, sigue estos pasos:

1. Abre una terminal en la raíz del proyecto.

2. Ejecuta el siguiente comando para compilar y construir el proyecto:
```shell
mvn clean install
```
3. Una vez que la compilación sea exitosa, puedes ejecutar la aplicación con el siguiente comando:
```shell
mvn spring-boot:run
```
La aplicación se ejecutará en http://localhost:8080.

## Peticiones en Postman
Para probar las funcionalidades de la aplicación, puedes utilizar Postman para realizar solicitudes HTTP a los endpoints proporcionados por los controladores.

### Ejemplos de Peticiones
1. Obtener película por ID:
```json
GET http://localhost:8080/films/{id}
```
Esta petición retornará la película con el ID enviado y guardará la información solicitada en la base de datos

2. Actualizar la información de una película:
```json
PUT http://localhost:8080/films/{episodeId}
{
"title": "Updated Title",
"releaseDate": "2023-11-30"
}
```
Esta petición permite actualizar la información que guardamos en nuestra base de datos, para esto es necesario pasar el episode_id ya que este es el que se está tomando como identificador del registro de las películas en nuestra base de datos.

3. Eliminar un registro de la base de datos:
```json
DELETE http://localhost:8080/films/{episodeId}
```
Esta petición permite eliminar la información que guardamos en nuestra base de datos, para esto es necesario pasar el episode_id ya que este es el que se está tomando como identificador del registro de las películas en nuestra base de datos.

En este mismo repositorio se encuentra una colección de postman con las peticiones anteriormente mencionadas: https://github.com/Tulio-Rangel/starwars/blob/main/Starwars%20API.postman_collection.json