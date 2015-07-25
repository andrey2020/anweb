package net.andreynikolaev.anweb.service;

import java.io.Serializable;
import net.andreynikolaev.anweb.DAO.SystemProfilesDAO;
import net.andreynikolaev.anweb.db.Profiles;
import net.andreynikolaev.anweb.dbutil.EntityDAO;
import net.andreynikolaev.anweb.dbutil.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("systemProfilesService")
public class SystemProfilesService extends EntityService<Profiles> implements  Serializable{

    @Autowired
    SystemProfilesDAO systemProfilesDAO;
    
    @Override
    public EntityDAO getEntityDAO() {
        return systemProfilesDAO;
    }

    @Override
    public void setEntityDAO(EntityDAO entityDAO) {
        systemProfilesDAO = (SystemProfilesDAO) entityDAO;
    }

}
