/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.controller;

import java.io.Serializable;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.CayenneRuntime;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author andrey
 */
@Controller
@Scope("singleton")
public class SystemAnWeb implements Serializable{
    private ObjectContext context;
    
    public SystemAnWeb(){
        CayenneRuntime cr = new ServerRuntime("cayenne-AnWeb.xml");
        context = cr.newContext();
    }
    
    public ObjectContext getContext() {
        return context;
    }

    public void setContext(ObjectContext context) {
        this.context = context;
    }
    
}
