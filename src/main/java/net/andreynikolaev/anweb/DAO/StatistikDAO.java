/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.DAO;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import net.andreynikolaev.anweb.controller.SystemAnWeb;
import net.andreynikolaev.anweb.db.Statistik;
import net.andreynikolaev.anweb.dbutil.EntityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andrey
 */
@Repository
public class StatistikDAO extends EntityDAO<Statistik> implements  Serializable{
    private static final long serialVersionUID = 1L;
    
    
    @Autowired
    private SystemAnWeb system;
    
    public StatistikDAO(){
        
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
