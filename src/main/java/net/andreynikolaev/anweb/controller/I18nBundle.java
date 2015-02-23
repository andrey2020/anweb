/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.controller;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;
import net.andreynikolaev.anweb.dbutil.EntityService;
import net.andreynikolaev.anweb.db.I18nSystem;
import net.andreynikolaev.anweb.dbutil.IdGenerator;
import net.andreynikolaev.anweb.service.I18nService;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author andrey
 *  
 */
@Controller(value = "msg")
@Scope("singleton")
public class I18nBundle extends ResourceBundle implements Serializable{
    private static final long serialVersionUID = 1L;	     
    
    @Autowired
    @Qualifier("i18nService")
    EntityService i18nService;
    
    private Map<String, String> values = new HashMap<>();
    
    public I18nBundle(){
       /* List<I18nEntity> listEntity = (List<I18nEntity>) getI18nService().getEntityList();
        for(I18nEntity resource : listEntity) {
            values.put(resource.getKeyLocale().getKey(), resource.getValue());
        }*/
    }
    
    public EntityService getI18nService() {
        return i18nService;
    }

    public void setI18nService(I18nService i18nService) {
        this.i18nService = i18nService;
    }

    private Locale locale;

    
    public I18nBundle(Locale locale){
        this.locale = locale;
    }

    @Override
    protected Object handleGetObject(String key){
        this.locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        Object[] mKey = {IdGenerator.getIdbyString(key + locale.getISO3Language().substring(0, 2)),key, locale.getISO3Language().substring(0, 2)};
        I18nSystem i18n = (I18nSystem) getI18nService().getEntityById(mKey);
        if(i18n.getShow())
            return i18n.getI18nValue();
        else
        
       return "NULL";
    }

    @Override
    public Enumeration<String> getKeys() {
        /*List<I18nSystem> listEntity = (List<I18nSystem>) getI18nService().getEntityList();
        listEntity.stream().forEach((resource) -> {
            values.put(resource.getI18nKey(), resource.getI18nValue());
        });
                */
        return null;//IteratorUtils.asEnumeration(values.keySet().iterator());
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
    
    @Override
    public Locale getLocale() {
       return this.locale;
    }


}
