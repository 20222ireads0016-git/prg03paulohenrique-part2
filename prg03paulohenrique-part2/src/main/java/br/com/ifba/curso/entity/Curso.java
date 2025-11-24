/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

import br.com.ifba.infraestructure.entity.PersistenceEntity;
import java.io.Serializable;

/**
 *
 * @author paulo
 */
@Entity
@Table(name = "cursos")
public class Curso extends PersistenceEntity implements Serializable{
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "codigo_curso", nullable = false, unique = true)
    private String codigoCurso;
    
    @Column(name = "ativo")
    boolean ativo;
    
    public Curso(String nome, String codigoCurso){
        setCodigoCurso(codigoCurso);
        setNome(nome);
    }
    
    public Curso(){
        
    }
    

    public String getNome() {
        return nome;
    }

    public final void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public final void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
