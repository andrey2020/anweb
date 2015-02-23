/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.jsfutil;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import org.hibernate.HibernateException;
//import org.hibernate.engine.spi.FilterDefinition;

/**
 *
 * @author andrey
 */
public class URLFilter implements Filter { 

    @Override
    public void destroy() { 
        System.out.println("DESTROY");
    // TODO Auto-generated method stub 

    } 

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException { 
        // TODO Auto-generated method stub 
        
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
     
        resp.encodeRedirectURL("/ergrgdfg");
        //resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        //return; //break filter chain, requested JSP/servlet will not be executed
     
     
        //propagate to next element in the filter chain, ultimately JSP/ servlet gets executed
        chain.doFilter(request, response);      

    } 


    @Override
    public void init(FilterConfig arg0) throws ServletException { 
        System.out.println("INIT");

    } 


} 


