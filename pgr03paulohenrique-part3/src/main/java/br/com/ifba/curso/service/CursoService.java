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
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

        
/**
 *
 * @author paulo
 */
@Service
@Slf4j
@NoArgsConstructor
public class CursoService implements CursoIService {
    
    @Autowired
    private CursoRepository cursoRepository;
    
    
    @Override
    @Transactional
    public Curso save(Curso curso) throws RuntimeException{
        
        log.info("Tentando salvar curso: " + curso.getNome());
        
        if(curso == null){
            log.info("Erro na tentativa: Dados não localizados");
            throw new RuntimeException("Dados não localizados.");
            
        } else if (curso.getId() != null){
            log.info("Erro na tentativa: Informações já encontradas na base de dados");
            throw new RuntimeException("Não foi possivel salvar dados: Informações já encontradas na base de dados.");
            
        } else if (cursoRepository.existsByCodigoCurso(curso.getCodigoCurso())){
            log.info("Erro na tentativa: Já existe um curso com o mesmo codigo");
            throw new RuntimeException("Não foi possivel salvar informações: Já existe um curso com o mesmo codigo.");
            
        } else if (curso.getCodigoCurso().isBlank() || curso.getNome().isBlank()){
            log.info("Erro na tentativa: Informações obrigatóras não preenchidas");
            throw new RuntimeException("Informações obrigatóras não preenchidas.");
            
        } else {
            try{
                return cursoRepository.save(curso);
                
            } catch (NoResultException e){
                log.info("Erro na tentativa: Erro de persistencia");
                throw new RuntimeException("Ocorreu um erro inesperado, não foi possivel salvar as informações. Tente novamente mais tarde.");
            }
        }
    }
    
    @Override
    @Transactional
    public Curso update(Curso curso) throws RuntimeException{
        
        log.info("Tentando alterar curso: " + curso.getNome());
        
        if(curso == null){
            log.info("Erro na tentativa: Dados não localizados");
            throw new RuntimeException("Dados não localizados");
            
        } else if (curso.getCodigoCurso().isBlank() || curso.getNome().isBlank()){
            log.info("Erro na tentativa: Informações obrigatóras não preenchidas");
            throw new RuntimeException("Informações obrigatóras não preenchidas");
            
        } else {
            try{
                return cursoRepository.save(curso);
                
            } catch (NoResultException e){
                log.info("Erro na tentativa: Erro de persistencia");
                throw new RuntimeException("Ocorreu um erro inesperado, não foi possivel atualizar as informações. Tente novamente mais tarde.");
            }
        }
    }
    
    @Override
    @Transactional
    public void delete(Curso curso) throws RuntimeException{
        
        log.info("Tentando apagar curso: " + curso.getNome());
         
        if (curso == null){
            log.info("Erro na tentativa: Dados não localizados");
            throw new RuntimeException("Dados não localizados");
            
        } else if (curso.getId() == null){
            log.info("Erro na tentativa: Não é possivel deletar um objeto que ainda não existe na base de dados");
            throw new RuntimeException("Não é possivel deletar um objeto que ainda não existe na base de dados");
            
        } else {
            try{
                cursoRepository.findById(curso.getId());
                cursoRepository.delete(curso);
                
            } catch (PersistenceException e){
                log.info("Erro na tentativa: Erro de persistencia");
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
