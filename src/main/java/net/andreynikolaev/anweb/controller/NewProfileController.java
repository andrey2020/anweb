/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import net.andreynikolaev.anweb.db.Profiles;
import net.andreynikolaev.anweb.dbutil.IdGenerator;
import net.andreynikolaev.anweb.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author andrey
 */
@Controller
@Scope("request")
public class NewProfileController implements Serializable{
    
    @Autowired
    @Qualifier("profileService")
    private ProfileService profileService;

    
    private String profileName;
    private String email;
    private String password;
    
    public void createNewProfile(){
        if(newProfile(this.profileName, this.email, this.password)){
        
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/" + this.profileName);
            } catch (IOException ex) {
                Logger.getLogger(NewProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Name already exist", this.profileName);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Boolean newProfile(String profileName, String email, String password) {
        if(getProfileService().getEntityById(IdGenerator.getIdbyString(profileName)) != null) return false;

        Profiles newProfile = new Profiles(profileName, email, password);
       
        getProfileService().addEntity(newProfile);
       
        return true;
    }
    


    public ProfileService getProfileService() {
        return profileService;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }
    
}
