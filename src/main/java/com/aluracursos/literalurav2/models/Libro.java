package com.aluracursos.literalurav2.models;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;


    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @ElementCollection (fetch = FetchType.EAGER) //Trae y almacena los elementos de la colección junto a laentidad principal

    private List<String> idiomas;

    @ElementCollection (fetch = FetchType.EAGER)
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

    @Override
    public String toString() {
        return "\n******************* Libro ******************" +
                "\nTitulo= '" + titulo + '\'' +
                "\nAutor= " + (autor != null ? autor.getNombre() : "N/A") +
                "\nIdiomas= " + idiomas +
                "\nCategorias= " + categorias +
                "\nNumeroDescargas= " + numeroDescargas +
                "\n";
    }

}