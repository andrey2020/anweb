/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.service;

import java.io.Serializable;
import net.andreynikolaev.anweb.db.Profiles;
import org.apache.cayenne.BaseContext;
import org.apache.cayenne.ObjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author andrey
 */
@Service("adminProfileService")
public class AdminProfileService implements  Serializable{
    
    @Autowired
    @Qualifier("langListService")
    private LangListService langListService;
    
    private Profiles selectedProfile;

    public void deleteProfile(Profiles profile){
        getContext().deleteObjects(profile);
        getContext().commitChanges();
    } 
    
    
    public Profiles getSelectedProfile() {
        return selectedProfile;
    }

    public void setSelectedProfile(Profiles selectedProfile) {
        this.selectedProfile = selectedProfile;
    }

    public LangListService getLangListService() {
        return langListService;
    }

    public void setLangListService(LangListService langListService) {
        this.langListService = langListService;
    }
    
    public ObjectContext getContext() {
        return BaseContext.getThreadObjectContext();
    } 
}
