/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.curso.dao;

import br.com.ifba.infraestructure.dao.GenericIDao;
import br.com.ifba.curso.entity.Curso;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author paulo
 */
@Repository
public interface CursoIDao extends GenericIDao<Curso>{
    public Curso findByCodigoCurso(String codigoCurso);
    public List<Curso> findByCodigoCursoOrNome(String pesquisa);
}
