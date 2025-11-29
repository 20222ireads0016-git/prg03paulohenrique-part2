/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.service;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.repository.CursoRepository;

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
    private CursoRepository cursoRepository;
    
    
    public CursoService(){
        
    }    
    
    @Override
    @Transactional
    public Curso save(Curso curso) throws RuntimeException{
        
        if(curso == null){
            throw new RuntimeException("Dados não localizados.");
            
        } else if (curso.getId() != null){
            throw new RuntimeException("Não foi possivel salvar dados, informações já encontradas na base de dados.");
            
        } else if (cursoRepository.existsByCodigoCurso(curso.getCodigoCurso())){
            throw new RuntimeException("Não foi possivel salvar informações, Já existe um curso com o mesmo codigo.");
            
        } else if (curso.getCodigoCurso().isBlank() || curso.getNome().isBlank()){
            throw new RuntimeException("Informações obrigatóras não preenchidas.");
            
        } else {
            try{
                return cursoRepository.save(curso);
                
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
                return cursoRepository.save(curso);
                
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
                cursoRepository.findById(curso.getId());
                cursoRepository.delete(curso);
                
            } catch (PersistenceException e){
                throw new RuntimeException("Ocorreu um erro inesperado, não foi possivel deletar as informações. Tente novamente mais tarde.");
            }
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Curso> findAll() throws RuntimeException{
            return cursoRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Curso findById(Long ID) throws RuntimeException{
            return cursoRepository.findById(ID).orElse(null);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Curso findByCodigoCurso(String codigoCurso) throws RuntimeException{
            return cursoRepository.findByCodigoCurso(codigoCurso);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Curso> findByCodigoCursoOrNome(String pesquisa) throws RuntimeException{
            return cursoRepository.findByCodigoCursoOrNome(pesquisa);
    }
    
}
