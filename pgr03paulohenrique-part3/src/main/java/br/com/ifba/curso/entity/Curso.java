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
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 *
 * @author paulo
 */
@Entity
@Table(name = "cursos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class Curso extends PersistenceEntity implements Serializable{
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "codigo_curso", nullable = false, unique = true)
    private String codigoCurso;
    
    @Column(name = "ativo")
    boolean ativo;
}
