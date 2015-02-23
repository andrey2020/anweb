/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import net.andreynikolaev.anweb.DAO.LangListDAO;
import net.andreynikolaev.anweb.dbutil.EntityDAO;
import net.andreynikolaev.anweb.dbutil.EntityService;
import net.andreynikolaev.anweb.db.LangList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author andrey
 */
@Service("langListService")
@Component
@Transactional
public class LangListService extends EntityService<LangList> implements  Serializable{
    private static final long serialVersionUID = 995L;
    
    @Autowired
    LangListDAO langListDAO;
    
    @Override
    public EntityDAO getEntityDAO() {
        return this.langListDAO;
    }

    @Override
    public void setEntityDAO(EntityDAO entityDAO) {
        this.langListDAO = (LangListDAO) entityDAO;
    }
    
    public void languageCheck() {
        if(getEntityList().isEmpty()){
            addEntity(new LangList("Русский","ru"));
            addEntity(new LangList("Deutsch","de"));
            addEntity(new LangList("English","en"));
            addEntity(new LangList("Français","fr"));
            addEntity(new LangList("עברית","he"));
            
        }
        
    }
    
}
