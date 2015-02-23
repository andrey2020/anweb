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
import javax.servlet.http.Part;
import net.andreynikolaev.anweb.db.Experience;
import net.andreynikolaev.anweb.db.LangList;
import net.andreynikolaev.anweb.db.ProfileDetails;
import net.andreynikolaev.anweb.jsfutil.UtilSession;
import net.andreynikolaev.anweb.db.Profiles;
import net.andreynikolaev.anweb.db.Skills;
import net.andreynikolaev.anweb.db.SkillsGroup;
import net.andreynikolaev.anweb.dbutil.ImportProfileException;
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
    private static final long serialVersionUID = 1L;
    @Autowired
    private ApplicationController appController;
    
    @Autowired
    @Qualifier("profileService")
    private ProfileService profileService;
    
    private DualListModel<LangList> languagesList;
    private Profiles selectedProfile;
    
    private boolean profileChanged;
    
    private List<LangList> langList;
    
    private String password;
    private String newPassword;
    private List<Experience> bufferExperience = new ArrayList<>();
    private List<SkillsGroup> bufferSkillsGroup = new ArrayList<>();
    private List<Skills> bufferSkills = new ArrayList<>();
    
    private StreamedContent fileExport;
    private UploadedFile fileImport;
    
    private int activeindex;
    private String navAdminStatus = "mainAdmin";
    
    
    public AdminController(){
        
    }

    public List<LangList> getLangList() {
        return langList;
    }

    public void setLangList(List<LangList> langList) {
        this.langList = langList;
    }
    
    @PostConstruct
    public void init(){
        setSelectedProfile(getAppController().getSelectedProfile());
        langList = new ArrayList<>();
        langList.addAll(getSelectedProfile().getLangFromSysstem());
      
    }
    
    public void removeProfile(){
        getProfileService().deleteEntity(getSelectedProfile());
        logout();
    }

    public Profiles getSelectedProfile() {
        return selectedProfile;
    }

    public void setSelectedProfile(Profiles selectedProfile) {
        this.selectedProfile = selectedProfile;
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

    public ApplicationController getAppController() {
        return appController;
    }

    public void setAppController(ApplicationController appController) {
        this.appController = appController;
    }
    
    public Boolean getAdminPage(){
        boolean result = false;
        if(getSelectedProfile() != null && UtilSession.getLoginName().equals(getSelectedProfile().getProfileName())){
            result = true;
        }else{
            try {
                UtilSession.geExternalContext().redirect("/" + getAppController().getDefauldProfile());
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
    
    public void changeActiveLang(TransferEvent e){      
        if(e.isAdd()){
            getSelectedProfile().addLanguages((List<LangList>) e.getItems());            
        }else{
            getSelectedProfile().deleteLanguages((List<LangList>) e.getItems());
        }
        setProfileChanged(true);
    }
    
    public DualListModel<LangList> getLanguagesList() {
        
        List fL = new ArrayList();
        fL.addAll(langList);
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
    
    public void copyExperience(List<Experience> experiences){
        bufferExperience.clear();
        
        experiences.stream().forEach((ex) ->{
            bufferExperience.add(new Experience(ex));
            
        });
    }
    
    public void copySkillsGroup(List<SkillsGroup> skillsGroup){
        bufferSkillsGroup.clear();

        skillsGroup.stream().forEach((sg) -> {
            bufferSkillsGroup.add(sg.cloneSkillsGroup(sg));
        });
    }
    
    public void insertSkillsGroup(ProfileDetails detail){
        bufferSkillsGroup.stream().forEach((sk) ->{
            detail.addToSkillsGroups(sk);
            sk.getCloneSkills().stream().forEach((s) ->{             
                sk.addToSkills(new Skills(s));
               
            });
            sk.setCloneSkills(null);
        });
        bufferSkillsGroup.clear();
        
    }
    
    public void insertExperience(ProfileDetails detail){
        bufferExperience.stream().forEach((ex) ->{
            detail.addToExperiences(ex);
        });
        bufferExperience.clear();
        
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

    public List<Experience> getBufferExperience() {
        return bufferExperience;
    }

    public int getActiveindex() {
        return activeindex;
    }

    public void setActiveindex(int activeindex) {
        this.activeindex = activeindex;
    }

    public List<SkillsGroup> getBufferSkillsGroup() {
        return bufferSkillsGroup;
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

    public ProfileService getProfileService() {
        return profileService;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }
    
    public void updateUpdater(){
        RequestContext.getCurrentInstance().execute("updateUpdater();");
    }
    
    
}
