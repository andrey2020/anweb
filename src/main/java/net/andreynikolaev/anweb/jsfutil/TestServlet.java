/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.jsfutil;

import java.io.IOException;
import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.lifecycle.LifecycleFactory;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author andrey
 */
@WebServlet(name = "testServlet")
public class TestServlet extends HttpServlet {
    
    public TestServlet() {
        super();
    }
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }    
    //protected abstract void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        super.doGet(req, resp);
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        super.doPost(req, resp);
    }

    protected void log(FacesContext facesContext, String message) {
        facesContext.getExternalContext().log(message);
    }

    protected FacesContext getFacesContext(HttpServletRequest request,
        HttpServletResponse response) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext == null) {

            FacesContextFactory contextFactory  =
                (FacesContextFactory)FactoryFinder.getFactory(FactoryFinder.FACES_CONTEXT_FACTORY);
            LifecycleFactory lifecycleFactory =
                (LifecycleFactory)FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY); 
            javax.faces.lifecycle.Lifecycle lifecycle =
                lifecycleFactory.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);

            facesContext =
                contextFactory.getFacesContext(request.getSession().getServletContext(),
                    request, response, lifecycle);

            // Set using our inner class

            InnerFacesContext.setFacesContextAsCurrentInstance(facesContext);

            // set a new viewRoot, otherwise context.getViewRoot returns null
            UIViewRoot view =
            facesContext.getApplication().getViewHandler().createView(facesContext, "");
            facesContext.setViewRoot(view);        
        }
        return facesContext;
    }
    public void removeFacesContext() {
        InnerFacesContext.setFacesContextAsCurrentInstance(null);
    }
    protected Application getApplication(FacesContext facesContext) {
        return facesContext.getApplication();        
    }
    protected Object getManagedBean(String beanName, FacesContext
        facesContext) {        
    return
        getApplication(facesContext).getVariableResolver().resolveVariable(facesContext,
            beanName);
    }
    // You need an inner class to be able to call FacesContext.setCurrentInstance
    // since it's a protected method
    private abstract static class InnerFacesContext extends FacesContext {
        protected static void setFacesContextAsCurrentInstance(FacesContext
            facesContext) {
            FacesContext.setCurrentInstance(facesContext);
        }
    }     
}

