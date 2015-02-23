/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.Serializable;
import net.andreynikolaev.anweb.dbutil.EntityDAO;
import net.andreynikolaev.anweb.db.Statistik;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andrey
 */
@Repository
public class StatistikDAO extends EntityDAO<Statistik> implements  Serializable{
    private static final long serialVersionUID = 9954L;

    public StatistikDAO(){
    }


}
