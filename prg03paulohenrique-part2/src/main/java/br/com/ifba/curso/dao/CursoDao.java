/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.dao;

import br.com.ifba.infraestructure.dao.GenericDao;
import br.com.ifba.curso.entity.Curso;
import jakarta.persistence.NoResultException;

import jakarta.persistence.PersistenceException;
import java.util.List;

/**
 *
 * @author paulo
 */
public class CursoDao extends GenericDao<Curso> implements CursoIDao {
    
    @Override
    public Curso findByCodigoCurso(String codigoCurso){
        String jpql = "FROM Curso c WHERE c.codigoCurso = :codigoParam";
        
        try{
            return entityManager.createQuery(jpql, Curso.class).setParameter("codigoParam", codigoCurso).getSingleResult();
        } catch (NoResultException e){
            return null;
        } catch (PersistenceException e) {
            throw new PersistenceException(e);
        }
    }
    
    @Override
    public List<Curso> findByCodigoCursoOrNome(String pesquisa){
        String jpql = "FROM Curso c WHERE LOWER(c.codigoCurso) LIKE LOWER(:buscaParam) OR LOWER(c.nome) LIKE LOWER(:buscaParam)";
        
        try{
            return entityManager.createQuery(jpql, Curso.class).setParameter("buscaParam", "%" + pesquisa + "%").getResultList();
        } catch (PersistenceException e) {
            throw new PersistenceException(e);
        }
    }
}