/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.infraestructure.dao;
import br.com.ifba.infraestructure.entity.PersistenceEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import java.util.List;
import java.lang.reflect.ParameterizedType;

/**
 *
 * @author paulo
 * @param <Entity>
 */
public abstract class GenericDao<Entity extends PersistenceEntity> implements GenericIDao<Entity>{
    
    private static final EntityManagerFactory factory;
    protected EntityManager entityManager;
    private final Class<Entity> entityClass;
    
    static {
        factory = Persistence.createEntityManagerFactory("pgr03paulohenrique");
    }
    
    public GenericDao(){
        this.entityManager = factory.createEntityManager();
        this.entityClass = (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    @Override
    public Entity save(Entity entity){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            
            return entity;
            
        } catch (PersistenceException e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            
            throw new PersistenceException(e);
        }
    }
    
    @Override
    public Entity update(Entity entity){
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
            return entity;
        } catch (PersistenceException e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            throw new PersistenceException(e);
        }
    }
    
    @Override
    public void delete(Entity entity){
        try{
            Entity entityToBeRemoved = findById(entity.getId());
            
            if (entityToBeRemoved == null){
                return;
            }
            
            entityManager.getTransaction().begin();
            entityManager.remove(entityToBeRemoved);
            entityManager.getTransaction().commit();
            
        } catch (PersistenceException e){
            
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            
            throw new PersistenceException(e);
        }
    }
    
    @Override
    public List<Entity> findAll(){
        String jpql = "FROM " + entityClass.getSimpleName();
        
        try {
            return entityManager.createQuery(jpql, entityClass).getResultList();
        } catch (PersistenceException e){
            throw new PersistenceException(e);
        }
    }
    
    @Override
    public Entity findById(Long ID){
        try {
            return entityManager.find(entityClass, ID);
        } catch (PersistenceException e){
            throw new PersistenceException(e);
        }
    }
}
