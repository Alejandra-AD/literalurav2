# LITERALURA 📚

LITERALURA es una aplicación de consola que permite interactuar con la API de Gutendex para buscar y guardar libros en una base de datos. Utiliza Spring Boot, Hibernate y JPA para la persistencia de datos y Jackson para la conversión de JSON.

## Imágenes del proyecto

## Tecnologías utilizadas 
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white)
![Springboot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![IntellijIDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
	![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)


## Requisitos

- Java 19 o superior
- Maven 3.3.2 o superior
- Base de datos PostgreSQL

1. Configura la base de datos:

- Crea una base de datos en tu servidor de base de datos.
- Actualiza el archivo application.properties con la configuración de tu base de datos:
```sh
spring.datasource.url=jdbc:postgresql://localhost/literalura
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.datasource.driver-class-name = org.postgresql.Driver
hibernate.dialect = org.hibernate.dialect.HSQLDialect
spring.jpa.hibernate.ddl-auto=update

#cambiar sprig.jpa.show-sql = true para ver las consultas sql realizadas por hibernate
spring.jpa.show-sql = false
spring.jpa.format-sql = true
```

## Uso
Al iniciar la aplicación, se mostrará un menú con las siguientes opciones:

- **1. Mostrar lista de algunos libros en Gutendex**: Muestra una lista de libros obtenidos de la API de Gutendex.
- **2. Buscar libro por título**: Permite buscar un libro por su título y muestra el primer resultado encontrado. Si el libro se encuentra, se guarda en la base de datos.
- **3. Mostrar títulos guardados**: Muestra una lista de los libros que han sido guardados en la base de datos.
- **4. Mostrar lista de autores**: Muestra una lista con los autores que han sido registrados en la base de datos.
- **5. Mostrar lista de autores vivos por año**: Permite buscar autores vivos según fecha ingresada por el usuario.
- **6. Busque de libro por idioma**: Permite buscar libros por el idioma.(Inglés,Español,Frances)
- **0. Salir**: Termina la ejecución de la aplicación.
   
## Estructura del proyecto

```sh
src
└── main
    ├── java
    │   └── com
    │       └── aluracursos
    │           └── literalurav2
    │               ├── models
    │               │   ├── Autor (Clase)
    │               │   ├── Datos(Record)
    │               │   ├── DatosAutor(Record)
    │               │   ├── DatosLibro (Record)
    │               │   └── Libro (Clase)
    │               ├── principal
    │               │   └── Principal(Clase)
    │               ├── repository
    │               │   └── LibroRepository (Interface)
    │               │   
    │               └── service
    │                   ├── ConsumoAPI (Clase)
    │                   ├── ConversorDatos(Clase)
    │                   └── IConversorDatos(Interface)
    └── resources
        └── application.properties
```

## Clases Principales
- Principal: Clase principal que contiene el menú y los métodos para interactuar con la API y la base de datos.
- Libro: Entidad que representa un libro. Contiene información como título, autor, idiomas, categorías y número de descargas.
- Autor: Entidad que representa un autor. Contiene información como nombre, fecha de nacimiento, fecha de muerte y lista de libros.
- Datos: Record que representa la respuesta de la API de Gutendex. Contiene una lista de objetos DatosLibro.
- DatosLibro: Record que representa los datos de un libro obtenidos de la API de Gutendex.
- DatosAutor: Record que representa los datos de un autor obtenidos de la API de Gutendex.
- LibroRepository: Interface de repositorio para la entidad Libro. Extienden JpaRepository para proporcionar métodos CRUD.
- ConsumoAPI: Clase que se encarga de consumir la API de Gutendex.
- ConversorDatos: Clase que se encarga de convertir las respuestas JSON de la API en objetos Java.
- IConversorDatos: Interface que nos ayuda a convertir datos en formato JSON a objetos Java de cualquier tipo.

## Contribuciones
Las contribuciones son bienvenidas. Para contribuir, por favor abre un issue o un pull request en el repositorio. 👋😊



