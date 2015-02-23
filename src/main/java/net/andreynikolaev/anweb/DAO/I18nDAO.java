/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.DAO;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import net.andreynikolaev.anweb.controller.SystemAnWeb;
import net.andreynikolaev.anweb.dbutil.EntityDAO;
import net.andreynikolaev.anweb.db.I18nSystem;
import net.andreynikolaev.anweb.dbutil.EntityService;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.access.DataContext;
import org.apache.cayenne.configuration.CayenneRuntime;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andrey
 */
@Repository
public class I18nDAO extends EntityDAO<I18nSystem> implements  Serializable{
    private static final long serialVersionUID = 9954L;
    
    @Autowired
    private SystemAnWeb system;
    
    public I18nDAO(){
        
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
