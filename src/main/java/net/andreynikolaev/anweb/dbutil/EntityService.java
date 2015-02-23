/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.dbutil;

import java.io.Serializable;
import java.util.List;
import org.apache.cayenne.CayenneDataObject;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author andrey
 * @param <E>
 */
@Transactional(readOnly = true)
public abstract class EntityService<E extends CayenneDataObject> implements Serializable{
    
    public void addEntity(E entity){
        getEntityDAO().addEntity(entity);
    }
    
    public E getEntityById(Object id){
        return (E) getEntityDAO().getEntityById(id);
    }
    
    public boolean deleteEntity(E entity){
        return getEntityDAO().deleteEntity(entity);
    }
    
    public boolean deleteAllEntity(){
        return getEntityDAO().deleteAllEntity();
    }
    
    public List<E> getEntityList(){
        return getEntityDAO().getEntityList();
    }
    
    public void save(){
        getEntityDAO().save();
    }
    
    abstract public EntityDAO getEntityDAO();

    abstract public void setEntityDAO(EntityDAO entityDAO);

}
