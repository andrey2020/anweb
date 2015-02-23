/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import net.andreynikolaev.anweb.db.I18nSystem;
import net.andreynikolaev.anweb.db.LangList;
import net.andreynikolaev.anweb.db.Profiles;
import net.andreynikolaev.anweb.db.Statistik;
import net.andreynikolaev.anweb.dbutil.EntityService;
import net.andreynikolaev.anweb.jsfutil.HttpSessionChecker;
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
@Scope("session")
public class SystemController implements Serializable{
    
    @Autowired
    @Qualifier("statistikService")
    private EntityService statistikService;
    
    @Autowired
    @Qualifier("langListService")
    private EntityService langListService;
    
    @Autowired
    @Qualifier("i18nService")
    private EntityService i18nService;
    
    @Autowired
    @Qualifier("profileService")
    private ProfileService profileService;
    
    private List<HttpSession> httpSessionList = new ArrayList<>();
            
    public List getProfileList() {
        return profileService.getEntityList();
    }
    public void deleteProfile(Profiles profile) {
        getProfileService().deleteEntity(profile);
    }    
    public void deleteAllProfile() {
        getProfileService().deleteAllEntity();
    }
    
    public void setProfileSuper(Profiles profile) {
        profileService.setProfileSuper(profile);
    }
    public String getAllLangFromProfile(Profiles profile) {
     /*   final List<String> listStr = new ArrayList();
        profile.getAllLangFromProfile().stream().forEach((ll) -> {
            listStr.add(ll.getLangNativeName() ); 
        });
        return listStr.stream().reduce((t, u) -> t + ", " + u).get();
       */
        String str = "";
        str = profile.getAllLangFromProfile()
                .stream()
                .map((llist) -> llist.getLangNativeName() + ", ")
                .reduce(str, String::concat);
        return str;
    }
    
    
    
    public List getI18nList() {        
        return i18nService.getEntityList();
    }
    
    public void changeI18nShow(I18nSystem entity) {
        entity.setShow(!entity.getShow());
        i18nService.save();
    }
      
    public void saveI18nList() {
        getI18nService().save();
    }
    
    
    public void newI18n() {
        getI18nService().addEntity(new I18nSystem());
    }
    public void deleteI18n(I18nSystem i18n) {
        checkDeleteError(getI18nService().deleteEntity(i18n));
    }    
    public void deleteAllI18n() {
        checkDeleteError(getI18nService().deleteAllEntity());
    }    
    
    
    public List getStatistikList() {
        return statistikService.getEntityList();
    }
    public void deleteStatistik(Statistik statistik) {
        checkDeleteError(getStatistikService().deleteEntity(statistik));
    }    
    public void deleteAllStatistik() {
        checkDeleteError(getStatistikService().deleteAllEntity());
    }
    
    public List getLanguageList() {
        return langListService.getEntityList();
    }
    public void deleteLanguage(LangList language) {
        
        checkDeleteError(getLangListService().deleteEntity(language));

    }    
    public void deleteAllLanguage() {
        checkDeleteError(getLangListService().deleteAllEntity());
    }
    public void saveLanguageList() {
        getLangListService().save();
    }
    
    
    public void newLanguage() {
        getLangListService().addEntity(new LangList(true));
    }
    
    private void checkDeleteError(boolean result) {
        if (!result) 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Delete is impossible"));
    } 
    
    public synchronized Collection<HttpSession> getHttpSessionList() {
        if(
            (!httpSessionList.containsAll(HttpSessionChecker.getSessions().values())) ||
            HttpSessionChecker.getSessions().values().size() != httpSessionList.size()){
                httpSessionList = new ArrayList<>(HttpSessionChecker.getSessions().values());
        }
        return httpSessionList;
    }
    
    public SystemController(){
    
    }
    
    public boolean isMySession(String sessionId){
        return sessionId.equals(FacesContext.getCurrentInstance().getExternalContext().getSessionId(false));
    }
    
    public String getDateByLong(Long l){
        return new SimpleDateFormat("HH:mm:ss dd.MM.yy").format(new Date(l));
    }

    public void invalidateAll(){
        try{
        getHttpSessionList().parallelStream()
                .filter(s -> !s.getId().equals(FacesContext.getCurrentInstance().getExternalContext().getSessionId(false)))
                .forEach(HttpSession::invalidate);
        }catch(NullPointerException e){
            System.out.println("Null error");
        }

    }

    public EntityService getStatistikService() {
        return statistikService;
    }

    public void setStatistikService(EntityService statistikService) {
        this.statistikService = statistikService;
    }

    public EntityService getLangListService() {
        return langListService;
    }

    public void setLangListService(EntityService langListService) {
        this.langListService = langListService;
    }

    public EntityService getI18nService() {
        return i18nService;
    }

    public void setI18nService(EntityService i18nService) {
        this.i18nService = i18nService;
    }

    public ProfileService getProfileService() {
        return profileService;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    
}
