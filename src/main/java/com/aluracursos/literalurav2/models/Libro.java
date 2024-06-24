package com.aluracursos.literalurav2.models;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @ElementCollection
    private List<String> idiomas;

    @ElementCollection
    private List<String> categorias;

    private Double numeroDescargas;

    public Libro() {}

    public Libro(DatosLibro datos) {
        this.titulo = datos.titulo();
        if (datos.autores() != null && !datos.autores().isEmpty()) {
            DatosAutor datosAutor = datos.autores().get(0);
            this.autor = new Autor(datosAutor.nombre(), datosAutor.fechaNacimiento(), datosAutor.fechaMuerte());
        }
        this.idiomas = datos.idiomas();
        this.categorias = datos.temas();
        this.numeroDescargas = datos.numeroDescargas();
    }

    // Getters, setters y toString omitidos por brevedad


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }
}