package test;

import java.io.Serializable;
import net.andreynikolaev.anweb.dbutil.EntityDAO;
import net.andreynikolaev.anweb.db.Experience;

//import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andrey
 */
@Repository
public class ExperienceDAO extends EntityDAO<Experience> implements  Serializable{
    private static final long serialVersionUID = 9954L;
    
    public ExperienceDAO(){
        
    }


}
