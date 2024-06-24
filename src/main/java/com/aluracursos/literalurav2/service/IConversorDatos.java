package com.aluracursos.literalurav2.service;

public interface IConversorDatos {
    <T> T obtenerDatos(String json , Class <T> clase);
}
