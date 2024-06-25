package com.aluracursos.literalurav2.principal;

import com.aluracursos.literalurav2.models.*;
import com.aluracursos.literalurav2.repository.LibroRepository;
import com.aluracursos.literalurav2.service.ConsumoAPI;
import com.aluracursos.literalurav2.service.ConversorDatos;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConversorDatos convierteDatos = new ConversorDatos();
    private final String URL_BASE = "http://gutendex.com/books/";
    private final Scanner scanner = new Scanner(System.in);
    private LibroRepository libroRepository;
    private List<Autor>autores;

    public Principal(LibroRepository libroRepository){
        this.libroRepository = libroRepository;
    }

    public void mostarMenu() {

        try {
            boolean salir = false;
            while (!salir) {
                System.out.println("""
                    
                    LITERALURA*✍️(◔◡◔)
                    1- Mostrar lista de algunos libros en gutenex
                    2- Buscar libro web
                    3- Mostrar títulos guardados
                    4- Lista de autores
                    5- Lista de autores vivos en detenerminado año
                    6- Buscar titulo por idioma
                    0- Salir

                    Ingrese su opción
                    """);

                int opcionUsuario = scanner.nextInt();
                scanner.nextLine();

                switch (opcionUsuario) {
                    case 1 -> getDatos();
                    case 2 -> buscarLibroWeb();
                    case 3 -> mostrarTitulosBuscados();
                    case 4 -> mostrarListDeAutores();
                    case 5 -> mostrarListaDeAutoresVivos();
                    case 6 -> mostrarTituloPorIdioma();
                    case 0 -> salir = true;
                    default -> System.out.println("Opción no válida");
                }
            }
            scanner.close();

        }catch (InputMismatchException e){
            System.out.println("No seleccionó una opción correcta, cerrando el programa...\n");
        }

    }


    private Datos getDatos() {
        String json = consumoAPI.obtenerDatos(URL_BASE);
        Datos libros = convierteDatos.obtenerDatos(json, Datos.class);
        System.out.println(libros);
        return libros;
    }

    private void buscarLibroWeb() {

        try{

            System.out.println("Ingrese el título que desea buscar:");
            String tituloUsuario = scanner.nextLine();
            String json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloUsuario.replace(" ", "%20"));
            Datos datos = convierteDatos.obtenerDatos(json, Datos.class);

            //Obtener el primer libro de la lista de resultados
            /*Optional<DatosLibro> libroEncontrado = datos.libro().stream().findFirst();*/

            Optional<DatosLibro> libroEncontrado = datos.libro().stream()
                    .filter(t -> t.titulo().toLowerCase().contains(tituloUsuario.toLowerCase()))
                    .findFirst();

            if (libroEncontrado.isPresent()) {
                // Crear un nuevo libro utilizando los datos obtenidos
                Libro libro = new Libro(libroEncontrado.get());

                // Guardar el libro en el repositorio
                libroRepository.save(libro);

                //imprime libro
                System.out.println("Nuevo libro creado:\n" + libroEncontrado);

            } else {
                System.out.println("No se encontraron libros.");
            }

        }catch (InputMismatchException e){
            System.out.println("No se encontraron coincidencia con su búsqueda");

        }

    }

    private void mostrarTitulosBuscados(){
        System.out.println("\n****** Lista de obras buscadas ******");
        List<Libro>libros = libroRepository.findAll();

        libros.stream().forEach(System.out::println);

    }

    private List<Autor> busquedaDeAutores() {
        // Obtener todos los libros de la base de datos y extraer los autores asociados a cada libro
        autores = libroRepository.findAll().stream()
                .map(Libro::getAutor) // Extraer el autor de cada libro
                .filter(Objects::nonNull) // Filtrar para evitar autores nulos, lo cual evitará errores al mostrar la lista de autores
                .collect(Collectors.toMap(
                        Autor::getNombre,   // Utilizar el nombre del autor como identificador único en el mapa
                        autor -> autor,     // Función lambda para mantener la instancia completa del autor como valor correspondiente al nombre
                        (valorExistente, nuevoValor) -> valorExistente // En caso de nombres duplicados, mantener el valor existente
                ))
                .values() // Obtener solo las instancias únicas de autores
                .stream()
                .collect(Collectors.toList()); // Convertir de nuevo a una lista

        return autores;
    }


    private void mostrarListDeAutores() {
        busquedaDeAutores();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados");
        } else {
            System.out.println(autores);
        }
    }


    private void mostrarListaDeAutoresVivos(){
        busquedaDeAutores();
        try {
            System.out.println("Por favor escriba una fecha");
            var fechaUsuario = scanner.nextInt();
            scanner.nextLine();
            var autoresVivos = autores.stream()
                    .filter(autor -> (autor.getFechaNacimiento() <= fechaUsuario) && (autor.getFechaMuerte() >= fechaUsuario))
                    .collect(Collectors.toList());
            System.out.println(autoresVivos);

        }catch (NullPointerException e){
            System.out.println("No existen autores para esta búsqueda");
        }
    }


    private void mostrarTituloPorIdioma() {
        String mensaje = """
            Ingrese uno de los siguientes idiomas para su búsqueda:
            - Escriba 'en' (sin comillas) para buscar libros en inglés
            - Escriba 'sp' (sin comillas) para buscar libros en español
            - Escriba 'fr' (sin comillas) para buscar libros en francés
            """;
        System.out.println(mensaje);

        try {
            String idiomaUsuario = scanner.nextLine().toLowerCase(); // Obtener y limpiar la entrada del usuario

            switch (idiomaUsuario) {
                case "en":
                case "sp":
                case "fr":
                    buscarLibrosPorIdioma(idiomaUsuario);
                    break;
                default:
                    System.out.println("Opción no válida. Ingrese un idioma válido.");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error al procesar la entrada del usuario: " + e.getMessage());
        }
    }

    private void buscarLibrosPorIdioma(String idioma) {
        List<Libro> libros = libroRepository.findByIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma seleccionado.");
        } else {
            var idiomaElegido = idioma.equals("en")? "Inglés": idioma.equals("sp")? "Español": "Frances";
            System.out.println("Libros encontrados en " + idiomaElegido + ":");
            libros.forEach(libro -> System.out.println(libro));
        }
    }

    private void mostrarListaLibrosCoincidentes(String palabraClave, Datos datos) {
        System.out.println("Mostrando todos los títulos que contienen la palabra clave '" + palabraClave.toUpperCase() + "'\n" + datos);
    }


}
