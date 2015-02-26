/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.controller;

import net.andreynikolaev.anweb.jsfutil.HttpSessionChecker;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import net.andreynikolaev.anweb.jsfutil.UtilSession;
import net.andreynikolaev.anweb.db.Profiles;
import net.andreynikolaev.anweb.service.ProfileService;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author andrey
 */
@Controller
@Scope("view")
public final class ApplicationController implements Serializable
{
    
    @Autowired
    @Qualifier("profileService")
    private ProfileService profileService;
    
    private String password;
    
    private FacesContext context;
   
    private String localeCode;
    private String navStatus;
    
    private Integer newProfileId;

    private final String profileNameParametr;
    
    private final HashMap<String, Integer> profilesNameList = new HashMap<>();
    
   public ApplicationController() {
        this.navStatus = UtilSession.getNavStatus();
        this.localeCode = UtilSession.getLang();
        profileNameParametr = getContext().getExternalContext().getRequestParameterMap().get("pr");
    }

    public String getLocaleCode() {
        return localeCode;
    }
    

    public void init(){ 

        if(!UtilSession.getLoginName().equals(" ") && !UtilSession.getLoginName().equals(profileNameParametr)){
            try {
                getContext().getExternalContext().redirect("/" + UtilSession.getLoginName());
            } catch (IOException ex) {
                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            }
        }
        
        if(getProfilesNameList().containsKey(profileNameParametr)){

            setSelectedProfile(getProfilesNameList().get(profileNameParametr));
            UtilSession.startSession(profileNameParametr);
        }else{
            try{
                getContext().getExternalContext().invalidateSession();
                getContext().getExternalContext().redirect("/Create/NewProfile");
                return;
            } catch (IOException ex) {
                Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        localeCode = getSelectedProfile().setSelectedDetailsByLangName(localeCode);
        getContext().getViewRoot().setLocale(new Locale(localeCode));
        UtilSession.setLang(localeCode);   
    }
    
    public void changeLanguage(ActionEvent actionEvent){
        getContext().getViewRoot().setLocale(new Locale(actionEvent.getComponent().getId()));
        this.localeCode = actionEvent.getComponent().getId();
        this.localeCode = getSelectedProfile().setSelectedDetailsByLangName(this.localeCode);
      
        UtilSession.setLang(this.localeCode);     

    }
    
    public void navigate(ActionEvent actionEvent) {
        String page = actionEvent.getComponent().getId();
       
        this.navStatus = page;
        UtilSession.setNavStatus(navStatus);
        updateUpdater();
    }
    
    public void navigateTo(String actionEvent) {
       
        this.navStatus = actionEvent;  
        UtilSession.setNavStatus(navStatus);
        updateUpdater();
    }
    
    private void setSelectedProfile(Integer id){
        //selectedProfile = (Profiles) getProfileService().getProfileById(id);
        getProfileService().setSelectedProfileByID(id);//getProfileById(id);

    }
    
    public Profiles getSelectedProfile() {
        return getProfileService().getSelectedProfile();
        //return selectedProfile;
    }
    
    public void changeProfile(AjaxBehaviorEvent e){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        try {
            getContext().getExternalContext().redirect("/" + getProfileService().getProfileById(newProfileId).getProfileName());
        } catch (IOException ex) {
            Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void changeProfile(ValueChangeEvent e){
       newProfileId = (Integer) e.getNewValue();
    }
    
    public boolean getDisableLang(String locName){
        if ("".equals(this.localeCode)) localeCode = getContext().getViewRoot().getLocale().getISO3Language().substring(0, 2);
        return this.localeCode.equals(locName);
    }
    
    public boolean getDisablePoint(String navName){
        return this.navStatus.equals(navName);
    }
    
    public HashMap<String, Integer> getProfilesNameList(){
        if(profilesNameList.isEmpty()){    
            profilesNameList.clear();
            getProfileService().getProfileList().stream().forEach((p) -> {
                profilesNameList.put(p.getProfileName(), (Integer) p.getObjectId().getIdSnapshot().get("ID"));
            });
        }
        return profilesNameList;
    }
   
    public FacesContext getContext() {
        context = FacesContext.getCurrentInstance();
        return context;
    }

    public void setContext(FacesContext context) {
        this.context = context;
    }

    public String getNavStatus() {
        return navStatus;
    }

    public void setNavStatus(String navStatus) {
        this.navStatus = navStatus;
    }

    public String getDefauldProfile() {
        return "Andrey";
    }
    
    public void navigateToAdmin(){
        if(UtilSession.getLoginName() != null && UtilSession.getLoginName().equals(getSelectedProfile().getProfileName())){
            setNavStatus("admin");
        }else{
            setNavStatus("login");
        }
        UtilSession.setNavStatus(getNavStatus());
        updateUpdater();
    }
    
    public void login() {        
        if (getSelectedProfile().checkLogin(password) && HttpSessionChecker.alreadyLogined(getSelectedProfile().getProfileName())){
            
            password = "";
            setNavStatus("admin");
            UtilSession.setLoginName(getSelectedProfile().getProfileName());
            UtilSession.setNavStatus(getNavStatus());
            updateUpdater();

        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Authorization failed"));
        }
    }
    
    public void updateUpdater(){
        RequestContext.getCurrentInstance().execute("updateUpdater();");
    }
    
    public String getPassword() {
        return "";
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @PreDestroy
    public void destroy(){
        System.out.println("PreDestroying Session Bean 1");
    } 

    public ProfileService getProfileService() {
        return profileService;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    
}
