package net.andreynikolaev.anweb.service;

import java.io.Serializable;
import net.andreynikolaev.anweb.dbutil.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("systemService")
public class SystemService implements  Serializable{
    @Autowired
    @Qualifier("statistikService")
    private EntityService statistikService;
    
    @Autowired
    @Qualifier("langListService")
    private EntityService langListService;
    
    @Autowired
    @Qualifier("i18nService")
    private EntityService i18nService;
    
    @Autowired
    @Qualifier("systemProfilesService")
    private EntityService systemProfilesService;

    public EntityService getStatistikService() {
        return statistikService;
    }

    public void setStatistikService(EntityService statistikService) {
        this.statistikService = statistikService;
    }

    public EntityService getLangListService() {
        return langListService;
    }

    public void setLangListService(EntityService langListService) {
        this.langListService = langListService;
    }

    public EntityService getI18nService() {
        return i18nService;
    }

    public void setI18nService(EntityService i18nService) {
        this.i18nService = i18nService;
    }

    public EntityService getSystemProfilesService() {
        return systemProfilesService;
    }

    public void setSystemProfilesService(EntityService systemProfilesService) {
        this.systemProfilesService = systemProfilesService;
    }
}
