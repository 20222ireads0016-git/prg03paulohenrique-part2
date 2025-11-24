/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.dao;

import br.com.ifba.infraestructure.dao.GenericDao;
import br.com.ifba.curso.entity.Curso;
import java.awt.HeadlessException;

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
        } catch (HeadlessException e) {
            System.err.println("Erro ao buscar por codigo");
            return null;
        }
    }
}