/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import net.andreynikolaev.anweb.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author andrey
 */
@Controller
@Scope("request")
public class EmailSenderController {
    @Autowired
    private ApplicationController appController;
    
    @Autowired
    private EmailService email;
    
    private String msg;
    private String fromName;
    private String fromEmail;
    
    private String from;
    private String nameFrom;
    
    
    public void testEmail() throws InterruptedException{
   
       String em = getAppController().getSelectedProfile().getEmail();
        String toMsg = "Message from " + fromName + " (" + fromEmail + ")<br/>" + msg.replaceAll("\n", "<br/><br/>");
        email.send("noreply.andrey.nikolaev@gmail.com",
                em,
                "New Message From www.andrey-nikolaev.net/" + getAppController().getSelectedProfile().getProfileName(),
                toMsg
        );
        msg = "";
        fromName = "";
        fromEmail = "";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK!", "OK!"));

    }
    
    public void clearMsg(){
         msg = "";
    }

    public EmailService getEmail() {
        return email;
    }

    public void setEmail(EmailService email) {
        this.email = email;
    }

    public ApplicationController getAppController() {
        return appController;
    }

    public void setAppController(ApplicationController appController) {
        this.appController = appController;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getNameFrom() {
        return nameFrom;
    }

    public void setNameFrom(String nameFrom) {
        this.nameFrom = nameFrom;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }
    
}
