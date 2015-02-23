/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.DAO;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import net.andreynikolaev.anweb.dbutil.EntityDAO;
import net.andreynikolaev.anweb.db.LangList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andrey
 */
@Repository
public class LangListDAO extends EntityDAO<LangList> implements  Serializable{
     @Autowired
    private net.andreynikolaev.anweb.controller.SystemAnWeb system;
    
    public LangListDAO(){
        
    }
    
    @PostConstruct
    public void init(){
        setContext(getSystem().getContext());
    }

    public net.andreynikolaev.anweb.controller.SystemAnWeb getSystem() {
        return system;
    }

    public void setSystem(net.andreynikolaev.anweb.controller.SystemAnWeb system) {
        this.system = system;
    }
}
