/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.Serializable;
import test.StatistikDAO;
import net.andreynikolaev.anweb.dbutil.EntityDAO;
import net.andreynikolaev.anweb.dbutil.EntityService;
import net.andreynikolaev.anweb.db.Statistik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author andrey
 */
@Service("statistikServiceImpl")
@Component
public class StatistikServiceImpl extends EntityService<Statistik> implements  Serializable{
    private static final long serialVersionUID = 995L;	     
    
    @Autowired
    private StatistikDAO entityDAO;
    
    @Override
    public void setEntityDAO(EntityDAO entityDAO) {
        this.entityDAO = (StatistikDAO) entityDAO;
    }
    

    @Override
    public StatistikDAO getEntityDAO(){
        return this.entityDAO;
    }


}
