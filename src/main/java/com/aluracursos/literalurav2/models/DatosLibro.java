package com.aluracursos.literalurav2.models;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title")String titulo,
        @JsonAlias("authors") List<DatosAutor>autores,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("subjects") List <String> temas,
        @JsonAlias("download_count")Double numeroDescargas
) {

    public String toString(){
        return    "\n*********************************************"
                + "\nTitulo: " + titulo
                + "\nAutor: " + autores
                + "\nIdiomas: " + idiomas
                + "\nCategorías: " + temas
                + "\nNúmero de descargas: " + numeroDescargas
                + "\n";

    }

}
