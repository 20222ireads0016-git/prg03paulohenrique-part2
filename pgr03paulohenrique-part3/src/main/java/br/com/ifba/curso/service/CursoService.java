/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.service;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.dao.CursoIDao;
import jakarta.persistence.NoResultException;

import jakarta.persistence.PersistenceException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author paulo
 */
@Service
public class CursoService implements CursoIService {
    
    @Autowired
    private CursoIDao cursoDao;
    
    
    public CursoService(){
        
    }    
    
    @Override
    @Transactional
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
                
            } catch (NoResultException e){
                throw new RuntimeException("Ocorreu um erro inesperado, não foi possivel salvar as informações. Tente novamente mais tarde.");
            }
        }
    }
    
    @Override
    @Transactional
    public Curso update(Curso curso) throws RuntimeException{
        
        if(curso == null){
            throw new RuntimeException("Dados não localizados");
            
        } else if (curso.getCodigoCurso().isBlank() || curso.getNome().isBlank()){
            throw new RuntimeException("Informações obrigatóras não preenchidas");
            
        } else {
            try{
                return cursoDao.update(curso);
                
            } catch (NoResultException e){
                throw new RuntimeException("Ocorreu um erro inesperado, não foi possivel atualizar as informações. Tente novamente mais tarde.");
            }
        }
    }
    
    @Override
    @Transactional
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
                throw new RuntimeException("Ocorreu um erro inesperado, não foi possivel deletar as informações. Tente novamente mais tarde.");
            }
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Curso> findAll() throws RuntimeException{
        try{
            return cursoDao.findAll();
        } catch (NoResultException e){
            throw new RuntimeException("Ocorreu um erro inesperado, não foi possivel carregar as informações. Tente novamente mais tarde.");
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Curso findById(Long ID) throws RuntimeException{
        try{
            return cursoDao.findById(ID);
        } catch (NoResultException e){
            throw new RuntimeException("Ocorreu um erro inesperado, não foi possivel carregar as informações. Tente novamente mais tarde.");
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Curso findByCodigoCurso(String codigoCurso) throws RuntimeException{
        try{
            return cursoDao.findByCodigoCurso(codigoCurso);
        } catch (NoResultException e){
            throw new RuntimeException("Ocorreu um erro inesperado, não foi possivel carregar as informações. Tente novamente mais tarde.");
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Curso> findByCodigoCursoOrNome(String pesquisa) throws RuntimeException{
        try{
            return cursoDao.findByCodigoCursoOrNome(pesquisa);
        } catch (NoResultException e){
            throw new RuntimeException("Ocorreu um erro inesperado, não foi possivel carregar as informações. Tente novamente mais tarde.");
        }
    }
    
}
