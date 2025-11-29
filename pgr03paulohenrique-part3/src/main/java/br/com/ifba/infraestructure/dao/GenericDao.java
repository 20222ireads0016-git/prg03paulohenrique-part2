/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.infraestructure.dao;
import br.com.ifba.infraestructure.entity.PersistenceEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import java.util.List;
import java.lang.reflect.ParameterizedType;

/**
 *
 * @author paulo
 * @param <Entity>
 */
public abstract class GenericDao<Entity extends PersistenceEntity> implements GenericIDao<Entity>{
    
    @PersistenceContext
    protected EntityManager entityManager;
    private final Class<Entity> entityClass;
    
    public GenericDao(){
        this.entityClass = (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    @Override
    public Entity save(Entity entity){
        try{
            entityManager.persist(entity);
            return entity;
        } catch (NoResultException e) {
            return null;
        }
    }
    
    @Override
    public Entity update(Entity entity){
        try {
            return entityManager.merge(entity);
        } catch (NoResultException e) {
            return null;
        }
    }
    
    @Override
    public void delete(Entity entity){
        try{
            Entity entityToBeRemoved = entityManager.find(entityClass, entity.getId());

            if (entityToBeRemoved != null){
                entityManager.remove(entityToBeRemoved);
            }
        } catch (PersistenceException e){
            throw new PersistenceException(e);
        }
    }
    
    @Override
    public List<Entity> findAll(){
        try{
            return entityManager.createQuery("FROM " + entityClass.getSimpleName(), entityClass).getResultList();
        } catch (NoResultException e) {
            return null;
        }   
    }
    
    @Override
    public Entity findById(Long ID){
        try{
            return entityManager.find(entityClass, ID);
        } catch (NoResultException e) {
            return null;
        }   
    }
}
