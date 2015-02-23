/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.service;

import java.io.Serializable;
import java.util.List;
import net.andreynikolaev.anweb.DAO.ProfileDAO;
import net.andreynikolaev.anweb.db.LangList;
import net.andreynikolaev.anweb.db.Profiles;
import net.andreynikolaev.anweb.dbutil.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author andrey
 */
@Service("profileService")
@Component
@Transactional
public class ProfileService implements  Serializable{
    private static final long serialVersionUID = 995L;	     
    
    @Autowired
    private ProfileDAO entityDAO;
    
    @Autowired
    @Qualifier("langListService")
    private LangListService langListService;
    
 
    public void setEntityDAO(ProfileDAO entityDAO) {
        this.entityDAO = (ProfileDAO) entityDAO;
    }
    

    public ProfileDAO getEntityDAO(){
        return this.entityDAO;
    }
    
    @Transactional()
    public void addEntity(Profiles entity){
        getLangListService().languageCheck();
        entity.setDefLang(getLangByLocale("en"));
        getEntityDAO().addEntity(entity);
    }
    
    @Transactional()
    public Profiles getEntityById(Object id){
        return (Profiles) getEntityDAO().getEntityById(id);
    }
    
    @Transactional()
    public void deleteEntity(Profiles entity){
        getEntityDAO().deleteEntity(entity);
    }
    
    @Transactional()
    public void deleteAllEntity(){
        getEntityDAO().deleteAllEntity();
    }
    
    @Transactional()
    public void updateEntity(Profiles entity){
        getEntityDAO().updateEntity(entity);
    }
    
    @Transactional()
    public List<Profiles> getEntityList(){
        return getEntityDAO().getEntityList();
    }
    
    public Profiles getProfileByName(String profileName){
        return getEntityDAO().getEntityByName(profileName);
    }
    
    public LangList getLangByLocale(String locale){
        return getEntityDAO().getLangByLocale(locale);
    }

    public LangListService getLangListService() {
        return langListService;
    }

    public void setLangListService(LangListService langListService) {
        this.langListService = langListService;
    }
    
    public void setProfileSuper(Profiles profile) {
        profile.setSuperAdmin(!profile.getSuperAdmin());
        getEntityDAO().getContext().commitChanges();
    }

    
}
