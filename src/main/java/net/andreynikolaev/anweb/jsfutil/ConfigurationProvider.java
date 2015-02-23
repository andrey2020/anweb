/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.jsfutil;

import javax.servlet.ServletContext;
import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.servlet.config.Forward;

import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.Path;

/**
 *
 * @author andrey
 */
@RewriteConfiguration
public class ConfigurationProvider extends HttpConfigurationProvider
{
    
   @Override
   public int priority(){
     return 10;
   }

   @Override
   public Configuration getConfiguration(final ServletContext context){
       return ConfigurationBuilder.begin()                
               
               .addRule()
               .when(Path.matches("/Create/NewProfile"))
               .perform(Forward.to("/page/newProfile.xhtml"))
               
               .addRule()
               .when(Path.matches("/{pr}").withRequestBinding())
               .perform(Forward.to("/page/index.xhtml"))
               
               
               ;
    }
    
}

