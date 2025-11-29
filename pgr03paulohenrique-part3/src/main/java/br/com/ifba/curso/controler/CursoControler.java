  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.controler;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.service.CursoIService;
import org.springframework.stereotype.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author paulo
 */
@Controller
public class CursoControler implements CursoIControler{
    
    @Autowired
    private CursoIService cursoService;
    
    public CursoControler(){
        
    }
    
    
    @Override
    public Curso save(Curso curso) throws RuntimeException{
        return cursoService.save(curso);
    }
    
    @Override
    public Curso update(Curso curso) throws RuntimeException{
        return cursoService.update(curso);
    }
    
    @Override
    public void delete(Curso curso) throws RuntimeException{
        cursoService.delete(curso);
    }
    
    @Override
    public List<Curso> findAll() throws RuntimeException{
        return cursoService.findAll();
    }
    
    @Override
    public Curso findById(Long ID) throws RuntimeException{
        return cursoService.findById(ID);
    }
    
    @Override
    public Curso findByCodigoCurso(String codigoCurso) throws RuntimeException{
        return cursoService.findByCodigoCurso(codigoCurso);
    }
    
    @Override
    public List<Curso> findByCodigoCursoOrNome(String pesquisa) throws RuntimeException{
        return cursoService.findByCodigoCursoOrNome(pesquisa);
    }
    
}
