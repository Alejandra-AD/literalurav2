package com.aluracursos.literalurav2.repository;

import com.aluracursos.literalurav2.models.Autor;
import com.aluracursos.literalurav2.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    @Query("SELECT l.autor FROM Libro l WHERE l.autor.nombre = :nombre AND l.autor.fechaNacimiento = :fechaNacimiento " +
            "AND l.autor.fechaMuerte = :fechaMuerte")
    Autor findAutorByNombreAndFechas(String nombre, Integer fechaNacimiento, Integer fechaMuerte);


    // Consulta JPQL que selecciona todos los libros cuyo conjunto de idiomas incluye el idioma especificado.
    // :idioma es el parámetro que representa el idioma a buscar dentro de la colección de idiomas de cada libro.
    @Query("SELECT l FROM Libro l WHERE :idioma MEMBER OF l.idiomas")
    List<Libro> findByIdioma(String idioma);
}
