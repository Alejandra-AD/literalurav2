package com.aluracursos.literalurav2.repository;

import com.aluracursos.literalurav2.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro,Long> {
}
