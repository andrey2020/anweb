/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.service;

import java.io.Serializable;
import net.andreynikolaev.anweb.DAO.I18nDAO;
import net.andreynikolaev.anweb.dbutil.EntityDAO;
import net.andreynikolaev.anweb.dbutil.EntityService;
import net.andreynikolaev.anweb.db.I18nSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author andrey
 */

@Service("i18nService")
@Component
@Transactional
public class I18nService extends EntityService<I18nSystem> implements  Serializable{
    private static final long serialVersionUID = 995L;
    
    @Autowired
    I18nDAO i18nDAO;
    
    @Override
    public EntityDAO getEntityDAO() {
        return i18nDAO;
    }
    
    @Override
    public void setEntityDAO(EntityDAO entityDAO) {
        this.i18nDAO = (I18nDAO) entityDAO;
    }



    @Override
    public I18nSystem getEntityById(Object id) {
        Object[] mKey = (Object[]) id;
        Integer dbKey = (Integer) mKey[0];
        String key = (String) mKey[1];
        String locale = (String) mKey[2];
        I18nSystem result = (I18nSystem) getEntityDAO().getEntityById(dbKey);
        if(result != null){
            return result;
        }else{
            
            I18nSystem i18n = new I18nSystem();
            i18n.setID(dbKey);
            i18n.setI18nKey(key);
            i18n.setI18nLocale(locale);
            i18n.setI18nValue("# "+ i18n.getI18nKey() +" #" );
            i18n.setShow(true);
            
            addEntity(i18n);
            result = (I18nSystem) getEntityDAO().getEntityById(dbKey);
            return result;
        }
    }
    
    public void changeEntityShow(I18nSystem entity) {
        entity.setShow(!entity.getShow());
    }
}
