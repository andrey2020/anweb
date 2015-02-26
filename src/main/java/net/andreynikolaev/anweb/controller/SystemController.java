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
import net.andreynikolaev.anweb.jsfutil.HttpSessionChecker;
import net.andreynikolaev.anweb.service.SystemService;
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
    @Qualifier("systemService")
    private SystemService systemService;

    
    private List<HttpSession> httpSessionList = new ArrayList<>();
            
    public List getProfileList() {
        return getSystemService().getSystemProfilesService().getEntityList();
    }
    public void deleteProfile(Profiles profile) {
        getSystemService().getSystemProfilesService().deleteEntity(profile);
    }    
    public void deleteAllProfile() {
        getSystemService().getSystemProfilesService().deleteAllEntity();
    }

    public String getAllLangFromProfile(Profiles profile) {
        String str = "";
        str = profile.getAllLangFromProfile()
                .stream()
                .map((llist) -> llist.getLangNativeName() + ", ")
                .reduce(str, String::concat);
        return str;
    }
    
    
    
    public List getI18nList() {        
        return getSystemService().getI18nService().getEntityList();
    }
      
    public void saveI18nList() {
        getSystemService().getI18nService().save();
    }
    public void newI18n() {
        getSystemService().getI18nService().addEntity(new I18nSystem());
    }
    public void deleteI18n(I18nSystem i18n) {
        checkDeleteError(getSystemService().getI18nService().deleteEntity(i18n));
    }    
    public void deleteAllI18n() {
        checkDeleteError(getSystemService().getI18nService().deleteAllEntity());
    }    

    
    
    public void deleteStatistik(Statistik statistik) {
        checkDeleteError(getSystemService().getStatistikService().deleteEntity(statistik));
    }    
    public void deleteAllStatistik() {
        checkDeleteError(getSystemService().getStatistikService().deleteAllEntity());
    }
    

    
    public void deleteLanguage(LangList language) {        
        checkDeleteError(getSystemService().getLangListService().deleteEntity(language));

    }    
    public void deleteAllLanguage() {
        checkDeleteError(getSystemService().getLangListService().deleteAllEntity());
    }
    public void saveLanguageList() {
        getSystemService().getLangListService().save();
    }
    
    
    public void newLanguage() {
        getSystemService().getLangListService().addEntity(new LangList(true));
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

    public SystemService getSystemService() {
        return systemService;
    }

    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }

    
}
