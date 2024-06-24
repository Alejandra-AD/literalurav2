package com.aluracursos.literalurav2.principal;

import com.aluracursos.literalurav2.models.Datos;
import com.aluracursos.literalurav2.models.DatosLibro;
import com.aluracursos.literalurav2.models.Libro;
import com.aluracursos.literalurav2.repository.LibroRepository;
import com.aluracursos.literalurav2.service.ConsumoAPI;
import com.aluracursos.literalurav2.service.ConversorDatos;

import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConversorDatos convierteDatos = new ConversorDatos();
    private final String URL_BASE = "http://gutendex.com/books/";
    private final Scanner scanner = new Scanner(System.in);
    private LibroRepository repositorio;
    public Principal(LibroRepository repository){
        this.repositorio = repository;
    }

    public void mostarMenu() {

        boolean salir = false;
        while (!salir) {
            System.out.println("""
                    
                    LITERALURA*✍️(◔◡◔)
                    1- Mostrar lista de algunos libros en gutenex
                    2- Buscar libro web
                    3- Mostrar titulos guardados
                    4- Buscar títulos por autor
                    5- Buscar títulos por idioma
                    0- Salir

                    Ingrese su opción
                    """);

            int opcionUsuario = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de nextInt()

            switch (opcionUsuario) {
                case 1 -> getDatos();
                case 2 -> buscarLibroWeb();
                case 3 -> mostrarTitulosBuscados();
                /*
                case 3 -> ;
                case 4 -> mostrarTitulosEncontradosPorAutor();
                case 5 -> mostrarTitulosPorNombreIdioma();*/
                case 0 -> salir = true;
                default -> System.out.println("Opción no válida");
            }
        }
        scanner.close(); // Cerrar Scanner cuando ya no se necesite
    }

    private Datos getDatos() {
        String json = consumoAPI.obtenerDatos(URL_BASE);
        Datos libros = convierteDatos.obtenerDatos(json, Datos.class);
        System.out.println(libros);
        return libros;
    }

    private void buscarLibroWeb() {


        System.out.println("Ingrese el título que desea buscar:");
        String tituloUsuario = scanner.nextLine();
        String json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloUsuario.replace(" ", "%20"));
        Datos datos = convierteDatos.obtenerDatos(json, Datos.class);

        /* Obtener el primer libro de la lista de resultados*/
        /*Optional<DatosLibro> libroEncontrado = datos.libro().stream().findFirst();*/

        Optional<DatosLibro> libroEncontrado = datos.libro().stream()
                .filter(t -> t.titulo().toLowerCase().contains(tituloUsuario.toLowerCase()))
                .findFirst();

        if (libroEncontrado.isPresent()) {
            // Crear un nuevo libro utilizando los datos obtenidos
            Libro libro = new Libro(libroEncontrado.get());

            // Guardar el libro en el repositorio
            repositorio.save(libro);
            //imprime libro
            System.out.println("Nuevo libro creado:\n" + libroEncontrado);

        } else {
            System.out.println("No se encontraron libros.");
        }
    }
    private void mostrarTitulosBuscados(){

    }

    private void mostrarListaLibrosCoincidentes(String palabraClave, Datos datos) {
        System.out.println("Mostrando todos los títulos que contienen la palabra clave '" + palabraClave.toUpperCase() + "'\n" + datos);
    }


}
