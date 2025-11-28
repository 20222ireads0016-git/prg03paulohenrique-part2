/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.service;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.dao.CursoIDao;
import br.com.ifba.curso.dao.CursoDao;

import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import java.util.List;

/**
 *
 * @author paulo
 */
public class CursoService implements CursoIService {
    
    private final CursoIDao cursoDao = new CursoDao();
    
    
    @Override
    public Curso save(Curso curso) throws RuntimeException{
        
        if(curso == null){
            throw new RuntimeException("Dados não localizados.");
            
        } else if (curso.getId() != null){
            throw new RuntimeException("Não foi possivel salvar dados, informações já encontradas na base de dados.");
            
        } else if (cursoDao.findByCodigoCurso(curso.getCodigoCurso()) != null){
            throw new RuntimeException("Não foi possivel salvar informações, Já existe um curso com o mesmo codigo.");
            
        } else if (curso.getCodigoCurso().isBlank() || curso.getNome().isBlank()){
            throw new RuntimeException("Informações obrigatóras não preenchidas.");
            
        } else {
            try{
                return cursoDao.save(curso);
                
            } catch (PersistenceException e){
                e.printStackTrace();
                throw new RuntimeException("Ocorreu um erro inesperado, não foi possivel salvar as informações. Tente novamente mais tarde.");
            }
        }
    }
    
    @Override
    public Curso update(Curso curso) throws RuntimeException{
        
        if(curso == null){
            throw new RuntimeException("Dados não localizados");
            
        } else if (curso.getCodigoCurso().isBlank() || curso.getNome().isBlank()){
            throw new RuntimeException("Informações obrigatóras não preenchidas");
            
        } else {
            try{
                return cursoDao.update(curso);
                
            } catch (PersistenceException e){
                e.printStackTrace();
                throw new RuntimeException("Ocorreu um erro inesperado, não foi possivel atualizar as informações. Tente novamente mais tarde.");
            }
        }
    }
    
    @Override
    public void delete(Curso curso) throws RuntimeException{
         
        if (curso == null){
            throw new RuntimeException("Dados não localizados");
        } else if (curso.getId() == null){
            throw new RuntimeException("Não é possivel deletar um objeto que ainda não foi salvo na base de dados");
        } else {
            try{
                cursoDao.findById(curso.getId());
                cursoDao.delete(curso);
                
            } catch (PersistenceException e){
                e.printStackTrace();
                throw new RuntimeException("Ocorreu um erro inesperado, não foi possivel deletar as informações. Tente novamente mais tarde.");
            }
        }
    }
    
    @Override
    public List<Curso> findAll() throws RuntimeException{
        try{
            return cursoDao.findAll();
        } catch (PersistenceException e){
            e.printStackTrace();
            throw new RuntimeException("Ocorreu um erro inesperado, não foi possivel carregar as informações. Tente novamente mais tarde.");
        }
    }
    
    @Override
    public Curso findById(Long ID) throws RuntimeException{
        try{
            return cursoDao.findById(ID);
        } catch (PersistenceException e){
            e.printStackTrace();
            throw new RuntimeException("Ocorreu um erro inesperado, não foi possivel carregar as informações. Tente novamente mais tarde.");
        }
    }
    
    @Override
    public Curso findByCodigoCurso(String codigoCurso) throws RuntimeException{
        try{
            return cursoDao.findByCodigoCurso(codigoCurso);
        } catch (PersistenceException e){
            e.printStackTrace();
            throw new RuntimeException("Ocorreu um erro inesperado, não foi possivel carregar as informações. Tente novamente mais tarde.");
        }
    }
    
    @Override
    public List<Curso> findByCodigoCursoOrNome(String pesquisa) throws RuntimeException{
        try{
            return cursoDao.findByCodigoCursoOrNome(pesquisa);
        } catch (PersistenceException e){
            e.printStackTrace();
            throw new RuntimeException("Ocorreu um erro inesperado, não foi possivel carregar as informações. Tente novamente mais tarde.");
        }
    }
    
}
