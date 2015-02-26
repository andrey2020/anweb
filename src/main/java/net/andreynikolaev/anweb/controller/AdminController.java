/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import net.andreynikolaev.anweb.db.LangList;
import net.andreynikolaev.anweb.jsfutil.UtilSession;
import net.andreynikolaev.anweb.db.Profiles;
import net.andreynikolaev.anweb.dbutil.ImportProfileException;
import net.andreynikolaev.anweb.service.AdminProfileService;
import net.andreynikolaev.anweb.service.ProfileService;
import org.apache.cayenne.validation.ValidationException;
import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
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
public class AdminController implements Serializable{
    @SuppressWarnings("compatibility:-9182234059446501527")
    private static final long serialVersionUID = 1L;
    
    @Autowired
    @Qualifier("adminProfileService")
    private AdminProfileService adminProfileService;
    
        @Autowired
    @Qualifier("profileService")
    private ProfileService profileService;
    
    private DualListModel<LangList> languagesList;
    
    private boolean profileChanged;
    
    
    
    private String password;
    private String newPassword;


    private transient StreamedContent fileExport;
    private transient UploadedFile fileImport;
    
    private int activeindex;
    private String navAdminStatus = "mainAdmin";
    
    
    public AdminController(){
        
    }
    
    @PostConstruct
    public void init(){
      
    }
    
    public void removeProfile(){
        getAdminProfileService().deleteProfile(getSelectedProfile());
        logout();
    }

    public Profiles getSelectedProfile() {
        return getProfileService().getSelectedProfile();
    }

   
    
    public void navigateInAdmin(ActionEvent actionEvent) {
        if(profileChanged){
          FacesContext.getCurrentInstance().addMessage(null, 
                  new FacesMessage(FacesMessage.SEVERITY_WARN, "Attention!", "Exist unsaved data"));  
        }else{
            String page = actionEvent.getComponent().getId();
            
            this.navAdminStatus = page;
            updateUpdater();
            RequestContext.getCurrentInstance().execute("updateOnSave();");
            //UtilSession.setNavStatus(navStatus);    
        }
    }
 
    public void logout() {
        if(profileChanged){
          FacesContext.getCurrentInstance().addMessage(null, 
                  new FacesMessage(FacesMessage.SEVERITY_WARN, "Attention!", "Exist unsaved data"));  
        }else{
        
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/" + getSelectedProfile().getProfileName());
            } catch (IOException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    public Boolean getAdminPage(){
        boolean result = false;
        if(getSelectedProfile() != null && UtilSession.getLoginName().equals(getSelectedProfile().getProfileName())){
            result = true;
        }else{
            try {
                UtilSession.geExternalContext().redirect("/Andrey");
            } catch (IOException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public String getNavAdminStatus() {
        return navAdminStatus;
    }

    public void setNavAdminStatus(String navAdminStatus) {
        this.navAdminStatus = navAdminStatus;
    }
    
    public void saveSelectedProfile(){
        try{
            getSelectedProfile().save();
            setProfileChanged(false);
            RequestContext.getCurrentInstance().execute("updateOnSave();");
        }catch(ValidationException e){
            e.getValidationResult().getFailures().stream().forEach(f ->{
                FacesContext.getCurrentInstance().addMessage(null, 
                  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attention!", f.getDescription()));
            });
            
        }

    }

    @SuppressWarnings("unchecked")
    public void changeActiveLang(TransferEvent e){      
        if(e.isAdd()){
            getSelectedProfile().addLanguages((List<LangList>) e.getItems());            
        }else{
            getSelectedProfile().deleteLanguages((List<LangList>) e.getItems());
        }
        setProfileChanged(true);
    }

    @SuppressWarnings("unchecked")
    public DualListModel<LangList> getLanguagesList() {
        
        List fL = new ArrayList();
        fL.addAll(getSelectedProfile().getLangFromSysstem());
        List pL = getSelectedProfile().getAllLangFromProfile();
        fL.removeAll(pL);
        languagesList = new DualListModel<>(fL, pL);
        return languagesList;
    }

    public void setLanguagesList(DualListModel<LangList> languagesList) {
        this.languagesList = languagesList;
    }
    
    public void photoUploadListener(FileUploadEvent event){
        try {
            getSelectedProfile().setPhoto(IOUtils.toByteArray(event.getFile().getInputstream()));
            setProfileChanged(true);
        } catch (IOException ex) {
            Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cancelChanges(){
        getSelectedProfile().rollbackChanges();
        setProfileChanged(false);
    }
    
    public void changePassword(){
        if (!getSelectedProfile().checkLogin(password)){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Authorization failed"));
        }else{
            getSelectedProfile().setPassword(newPassword);
            saveSelectedProfile();
            password = "";
            newPassword = "";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success.", "Your password has been changed!"));
        }
    }
    
    public void exportProfile(){
        fileExport = new DefaultStreamedContent(getSelectedProfile().getExportByteArrayInputStream(), 
                "text/xml",
                getSelectedProfile().getProfileName() + ".xml");
    }
    
    public void importProfile(FileUploadEvent event){
     
        try {
            getSelectedProfile().importFromInputStream(event.getFile().getInputstream());
            logout();
        } catch (IOException | ImportProfileException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "EROOR!", e.getMessage()));
        }
    }

    public boolean isProfileChanged() {
        
        return profileChanged;
    }

    public void setProfileChanged(boolean profileChanged) {
        this.profileChanged = profileChanged;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }



    public int getActiveindex() {
        return activeindex;
    }

    public void setActiveindex(int activeindex) {
        this.activeindex = activeindex;
    }


    public StreamedContent getFileExport() {
        return fileExport;
    }

    public UploadedFile getFileImport() {
        return fileImport;
    }

    public void setFileImport(UploadedFile fileImport) {
        this.fileImport = fileImport;
    }

   
    public void updateUpdater(){
        RequestContext.getCurrentInstance().execute("updateUpdater();");
    }

    public AdminProfileService getAdminProfileService() {
        return adminProfileService;
    }

    public void setAdminProfileService(AdminProfileService adminProfileService) {
        this.adminProfileService = adminProfileService;
    }

    public ProfileService getProfileService() {
        return profileService;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }
    
    public List<LangList> getLangList(){
        return getAdminProfileService().getLangListService().getEntityList();
    }
    
    
}
