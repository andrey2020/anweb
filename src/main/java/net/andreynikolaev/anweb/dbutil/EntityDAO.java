package net.andreynikolaev.anweb.dbutil;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import net.andreynikolaev.anweb.db.LangList;
import org.apache.cayenne.BaseContext;
import org.apache.cayenne.Cayenne;
import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.CayenneRuntimeException;
import org.apache.cayenne.DeleteDenyException;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.ObjectId;
import org.apache.cayenne.query.SelectQuery;
import org.apache.cayenne.query.UpdateBatchQuery;

/**
 *
 * @author andrey
 * @param <E>
 */
public abstract class EntityDAO<E extends CayenneDataObject> implements Serializable{
    
     private ObjectContext context;

    private final Class<?> entityClass = (Class<?>) (((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
   
    /**
     * Get Entity List
     * 
     */
    
    public EntityDAO(){
        
    }
    
    public List<E> getEntityList(){
                
        SelectQuery query = new SelectQuery(entityClass);
        List list = getContext().performQuery(query);
        return list;
    }
    
    
    /**
     * Add new Entity
     *
     * @param entity 
     */
    public void addEntity(E entity){
        getContext().registerNewObject(entity);
        save();
    }
    
    /**
     * Get Entity by ID
     *
     * @param  id Object
     * @return statistik
     */
    public E getEntityById(Object id){
        if(id instanceof ObjectId)
            return (E) Cayenne.objectForPK(getContext(), (ObjectId) id);
        return (E) Cayenne.objectForPK(getContext(), entityClass, id);
    }

    /**
     * Delete Entity
     *
     * @param entity
     */
    public boolean deleteEntity(E entity){
        try {
            getContext().deleteObjects(entity);
            save();
            return true;
        } catch (CayenneRuntimeException e) {
            return false;
        }        
    }
    
    /**
     * Delete all Entity
     *
     */
    public boolean deleteAllEntity(){
        try {
            getContext().deleteObjects(getEntityList());
            save();
            return true;
        } catch (CayenneRuntimeException e) {
            return false;
        }    
    }

    public ObjectContext getContext() {
        return context;
    }

    public void setContext(ObjectContext context) {
        this.context = context;
    }

    void save() {
        //UpdateBatchQuery uq = new UpdateBatchQuery(null, null, null, null, batchCapacity)
        getContext().commitChanges();
        
        try{
            getContext().commitChangesToParent();
        } catch (Exception e) 
        {}
    }
     
    
}
