# LITERALURA

LITERALURA es una aplicación de consola que permite interactuar con la API de Gutendex para buscar y guardar libros en una base de datos. Utiliza Spring Boot, Hibernate y JPA para la persistencia de datos y Jackson para la conversión de JSON.

## Requisitos

- Java 17 o superior
- Maven 3.6.3 o superior
- Base de datos PostgreSQL

1. Configura la base de datos:

- Crea una base de datos en tu servidor de base de datos.
- Actualiza el archivo application.properties con la configuración de tu base de datos:
```sh
spring.datasource.url=jdbc:mysql://localhost:3306/literalura
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
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



