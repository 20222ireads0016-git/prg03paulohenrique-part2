/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.infraestructure.dao;

import br.com.ifba.infraestructure.entity.PersistenceEntity;
import java.util.List;

/**
 *
 * @author paulo
 * @param <Entity>
 */
public interface GenericIDao<Entity extends PersistenceEntity> {
    
    public Entity save(Entity entity);
    
    public Entity update(Entity entity);
    
    public void delete(Entity entity);
    
    public List<Entity> findAll();
    
    public Entity findById(Long ID);
    
}
