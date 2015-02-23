/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import net.andreynikolaev.anweb.dbutil.EntityService;
import java.io.Serializable;
import test.ExperienceDAO;
import net.andreynikolaev.anweb.dbutil.EntityDAO;
import net.andreynikolaev.anweb.db.Experience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author andrey
 */
@Service("experienceServiceImplLocal")
@Component
@Transactional(readOnly = true)
public class ExperienceServiceImpl extends EntityService<Experience> implements  Serializable{
    private static final long serialVersionUID = 995L;	  
    
    @Autowired
    private ExperienceDAO entityDAO;
    
    public ExperienceServiceImpl(){
        
    }

    @Override
    public ExperienceDAO getEntityDAO(){
        return this.entityDAO;
    }
        
    @Override
    public void setEntityDAO(EntityDAO entityDAO) {
        this.entityDAO = (ExperienceDAO) entityDAO;
    }

    
}
