package com.aluracursos.literalurav2.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosAutor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer fechaNacimiento,
        @JsonAlias("death_year") Integer fechaMuerte
) {
    public String toString(){
        return   "\n    -Nombre: " + nombre
                +"\n    -Fecha nacimiento: " + fechaNacimiento
                +"\n    -Fecha muerte: " + fechaMuerte;
    }
}
