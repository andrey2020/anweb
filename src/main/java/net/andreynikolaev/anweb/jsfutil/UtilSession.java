/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.jsfutil;

import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author andrey
 */
public class UtilSession
{
    public static Map<String, Object> getSessionMap() {        
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    }
       
    public static HttpServletRequest getRequest() {
     return (HttpServletRequest) FacesContext.
        getCurrentInstance().
        getExternalContext().getRequest();
    }
    
    public static ExternalContext geExternalContext(){
        return FacesContext.getCurrentInstance().getExternalContext();
    }
 
    public static String getLoginName(){
      return (String) (getSessionMap().get("loginname") == null ? " " : getSessionMap().get("loginname"));
    }

    public static void setLoginName(String loginName){
        getSessionMap().put("loginname", loginName);
    }

    public static void setLang(String lang){
        setHistory(lang);
        getSessionMap().put("lang", lang);
    }

    public static String getLang(){
        return (String) (getSessionMap().get("lang") == null ? geExternalContext().getRequestLocale().getISO3Language().substring(0, 2) : getSessionMap().get("lang"));
    }

    public static void setNavStatus(String navStatus){
        setHistory(navStatus);
        getSessionMap().put("navstatus", navStatus);
    }
    
    public static void setIp(String ip){
        getSessionMap().put("ip", ip);
    }
    
    public static void setHistory(String hystory){
        if(getSessionMap().get("history") != null && ((String)getSessionMap().get("history")).length() > 200)
            getSessionMap().put("history", ".../" + ((String)getSessionMap().get("history")).substring(100));
        getSessionMap().put("history", (getSessionMap().get("history") == null ? hystory : getSessionMap().get("history") + ">" + hystory));
    }
    
    public static void setAgent(String agent){
        getSessionMap().put("agent", agent);
    }

    public static String getNavStatus(){
        return (String) (getSessionMap().get("navstatus") == null ? "info" : getSessionMap().get("navstatus"));
    }
    
    public static void startSession(String profileName){
        UtilSession.setAgent(UtilSession.getRequest().getHeader("user-agent"));
        UtilSession.setIp(UtilSession.getRequest().getRemoteAddr());
        UtilSession.setHistory("START:" + profileName);
    }
       

}
