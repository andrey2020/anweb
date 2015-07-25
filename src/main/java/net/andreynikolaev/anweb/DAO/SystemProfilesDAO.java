package net.andreynikolaev.anweb.DAO;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import net.andreynikolaev.anweb.controller.SystemAnWeb;
import net.andreynikolaev.anweb.db.Profiles;
import net.andreynikolaev.anweb.dbutil.EntityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andrey
 */
@Repository
public class SystemProfilesDAO extends EntityDAO<Profiles> implements  Serializable{
    private static final long serialVersionUID = 1L;
    
    
    @Autowired
    private SystemAnWeb system;
    
    public SystemProfilesDAO(){
        
    }
    
    @PostConstruct
    public void init(){
        setContext(getSystem().getContext());
    }

    public SystemAnWeb getSystem() {
        return system;
    }

    public void setSystem(SystemAnWeb system) {
        this.system = system;
    }
}
