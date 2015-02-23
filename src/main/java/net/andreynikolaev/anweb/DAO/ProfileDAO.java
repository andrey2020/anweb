/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.DAO;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import net.andreynikolaev.anweb.db.LangList;
import net.andreynikolaev.anweb.db.Profiles;
import net.andreynikolaev.anweb.dbutil.IdGenerator;
import org.apache.cayenne.BaseContext;
import org.apache.cayenne.Cayenne;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.SelectQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andrey
 */
@Repository
public class ProfileDAO implements  Serializable{
    private static final long serialVersionUID = 9954L;
    
    public ProfileDAO(){
        
    }

    
    /**
     * Get Entity List
     *
     * @return 
     */
    public List<Profiles> getEntityList(){
                
        SelectQuery query = new SelectQuery(Profiles.class);
        List list = getContext().performQuery(query);
        return list;
    }
    
    
    /**
     * Add new Entity
     *
     * @param entity 
     */
    public void addEntity(Profiles entity){
        getContext().registerNewObject(entity);
        getContext().commitChanges();
    }
    
    /**
     * Get Entity by ID
     *
     * @param  id Object
     * @return statistik
     */
    public Profiles getEntityById(Object id){
        return (Profiles) Cayenne.objectForPK(getContext(), Profiles.class, id);
    }

    /**
     * Delete Entity
     *
     * @param entity
     */
    public void deleteEntity(Profiles entity){
        getContext().deleteObjects(entity);
        getContext().commitChanges();
    }
    
    /**
     * Delete all Entity
     *
     */
    public void deleteAllEntity(){
        
        //Query q = getSessionFactory().getCurrentSession().createQuery("delete from " + entityName);
        //q.executeUpdate();
    }

    /**
     * Update Entity
     *
     * @param entity
     */
    public void updateEntity(Profiles entity){
        
        //getContext().
        //getSessionFactory().getCurrentSession().saveOrUpdate(entity);
    }

    public ObjectContext getContext() {
        return BaseContext.getThreadObjectContext();
    }

    public Profiles getEntityByName(String profileName) {
        SelectQuery query = new SelectQuery(Profiles.class, Profiles.PROFILE_NAME.eq(profileName));
        List<Profiles> list = getContext().performQuery(query);
        return list.get(0);
    }

    public LangList getLangByLocale(String locale) {
        return (LangList) Cayenne.objectForPK(getContext(), LangList.class, IdGenerator.getIdbyString(locale));
    }

    
}
