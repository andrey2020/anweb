/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.jsfutil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import net.andreynikolaev.anweb.controller.SystemAnWeb;
import net.andreynikolaev.anweb.db.Statistik;

/**
 *
 * @author andrey
 */
@WebListener
public class HttpSessionChecker implements HttpSessionListener {
    
    @Inject
    private SystemAnWeb systemAnWeb;
    
    private static final Map<String, HttpSession> sessions = new HashMap<>();

    public static Map<String, HttpSession> getSessions() {
        return sessions;
    }
    
    public static boolean alreadyLogined(String profileName){

        sessions.values().stream()
                .filter(s -> s.getAttribute("loginname") != null && s.getAttribute("loginname").equals(profileName))
                .findFirst()
                .ifPresent(HttpSession::invalidate);
        return true;
    }
    
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        event.getSource();
        HttpSession session = event.getSession();

        
        session.getServletContext();
        session.getServletContext().getContextPath();
        sessions.put(session.getId(), session);
        System.out.printf("Session ID %s created at %s%n", event.getSession().getId(), new Date());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        sessions.remove(event.getSession().getId());
        addStatistik(event.getSession());
        System.out.printf("Session ID %s destroyed at %s%n", event.getSession().getId(), new Date());
    }
    
    public void addStatistik(HttpSession session){

        Statistik statistik = new Statistik();
        
        statistik.setNavHistory((String) session.getAttribute("history"));           
        
        statistik.setSessionid(session.getId());
        statistik.setAgent((String) session.getAttribute("agent"));
        statistik.setAgentLang((String) session.getAttribute("lang"));
        statistik.setIpaddr((String) session.getAttribute("ip"));
        statistik.setTimeaccess(new Date(session.getCreationTime()));
        statistik.setLastAccess(new Date(session.getLastAccessedTime()));
        statistik.setLoginName((String) session.getAttribute("loginname"));
        
        getSystemAnWeb().getContext().registerNewObject(statistik);
        getSystemAnWeb().getContext().commitChanges();
    }

    public SystemAnWeb getSystemAnWeb() {
        return systemAnWeb;
    }

    public void setSystemAnWeb(SystemAnWeb systemAnWeb) {
        this.systemAnWeb = systemAnWeb;
    }

}
