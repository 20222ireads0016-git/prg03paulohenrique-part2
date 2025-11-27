/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.curso.service;

import br.com.ifba.curso.entity.Curso;
import java.util.List;

/**
 *
 * @author paulo
 */
public interface CursoIService {
    
    public Curso save(Curso curso) throws RuntimeException;
    
    public Curso update(Curso curso) throws RuntimeException;
    
    public void delete(Curso curso) throws RuntimeException;
    
    public List<Curso> findAll() throws RuntimeException;
    
    public Curso findById(Long ID) throws RuntimeException;
    
    public Curso findByCodigoCurso(String codigoCurso) throws RuntimeException;
    
    public List<Curso> findByCodigoCursoOrNome(String pesquisa) throws RuntimeException;
    
}
