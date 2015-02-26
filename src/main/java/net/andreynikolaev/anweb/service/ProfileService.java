package net.andreynikolaev.anweb.service;

import java.io.Serializable;

import java.util.List;

import net.andreynikolaev.anweb.db.LangList;
import net.andreynikolaev.anweb.db.Profiles;
import net.andreynikolaev.anweb.dbutil.IdGenerator;

import org.apache.cayenne.BaseContext;
import org.apache.cayenne.Cayenne;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.SelectQuery;

import org.springframework.stereotype.Service;

/**
 *
 * @author andrey
 */
@Service("profileService")
public class ProfileService implements  Serializable{
    @SuppressWarnings("compatibility:5243025266512447220")
    private static final long serialVersionUID = 1L;	     
    
    private Profiles selectedProfile;
    
    public void setSelectedProfileByID(Integer id) {
        this.selectedProfile = getProfileById(id);
    }
    
    public Profiles getSelectedProfile() {
        return selectedProfile;
    }
    
    /**
     * Add new Profile
     *
     * @param profile 
     */
    public void addProfile(Profiles profile){
        getContext().registerNewObject(profile);
        getContext().commitChanges();
    }

    /**
     * Get Profile List
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Profiles> getProfileList(){                
        return getContext().performQuery(new SelectQuery(Profiles.class));
    }
    
    /**
     * Get Profile by ID
     *
     * @param  id Object
     * @return 
     */
    public Profiles getProfileById(Object id){
        return (Profiles) Cayenne.objectForPK(getContext(), Profiles.class, id);
    }

    /**
     * Get Profile by Name
     *
     * @param profileName
     * @return 
     */
    public Profiles getProfileByName(String profileName) {
        return getProfileById(IdGenerator.getIdbyString(profileName));
    }

    public LangList getLangByLocale(String locale) {
        return (LangList) Cayenne.objectForPK(getContext(), LangList.class, IdGenerator.getIdbyString(locale));
    }
    
    public ObjectContext getContext() {
        return BaseContext.getThreadObjectContext();
    }    
}
