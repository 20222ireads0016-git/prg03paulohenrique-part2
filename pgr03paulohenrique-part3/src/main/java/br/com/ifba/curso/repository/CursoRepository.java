/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.curso.repository;

import br.com.ifba.curso.entity.Curso;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author paulo
 */
public interface CursoRepository extends JpaRepository<Curso, Long>{
    
    Curso findByCodigoCurso(String codigoCurso);
    
    boolean existsByCodigoCurso(String codigoCurso);
    
    @Query("SELECT c FROM Curso c WHERE LOWER(c.codigoCurso) LIKE LOWER(CONCAT('%', :pesquisa, '%')) OR LOWER(c.nome) LIKE LOWER(CONCAT('%', :pesquisa, '%'))")
    List<Curso> findByCodigoCursoOrNome(@Param("pesquisa") String pesquisa);
    
}
