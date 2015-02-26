package net.andreynikolaev.anweb.service;

import java.io.Serializable;
import net.andreynikolaev.anweb.db.I18nSystem;
import net.andreynikolaev.anweb.db.Profiles;
import net.andreynikolaev.anweb.dbutil.EntityDAO;
import net.andreynikolaev.anweb.dbutil.EntityService;
import org.springframework.stereotype.Service;

@Service("systemProfilesService")
public class SystemProfilesService extends EntityService<Profiles> implements  Serializable{

    @Override
    public EntityDAO getEntityDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEntityDAO(EntityDAO entityDAO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
