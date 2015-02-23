/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.dbutil;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.cayenne.BaseContext;
import org.apache.cayenne.Cayenne;
import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Component;

/**
 *
 * @author andrey
 */
@FacesConverter("net.andreynikolaev.anweb.entityConverter")
public class EntityConverter implements Converter
{
    
    private final String skillGroupEntityClassName = "SkillGroupEntity";
    
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        // {<ObjectId:LangList, ID=4>; committed; [langNativeName=>Français; profile=>?; toLang=>?; locale=>fr; localeName=>fr]}
        if(value.contains("ObjectId")){
            String str = value.split(";")[0].replace("}", "");
            str = str.replace("{", "");
            str = str.replace(">", "");
            str = str.replace("<", "");
            str = str.replace("ObjectId:", "");
            
            Integer id = Integer.valueOf(str.split(", ID=")[1]);
            String className = str.split(", ID=")[0];
            try {
                Class<?> cls = Class.forName("net.andreynikolaev.anweb.db." + className);
                
                return Cayenne.objectForPK(BaseContext.getThreadObjectContext(), cls, id);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EntityConverter.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return null;
    }

   
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }
    
}
