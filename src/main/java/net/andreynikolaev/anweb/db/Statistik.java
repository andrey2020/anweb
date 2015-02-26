package net.andreynikolaev.anweb.db;

import java.text.SimpleDateFormat;
import net.andreynikolaev.anweb.db.auto._Statistik;

public class Statistik extends _Statistik{

    public String getLastAccessFormat(){
        if(getLastAccess() != null)
            return new SimpleDateFormat("HH:mm:ss dd.MM.yy").format(getLastAccess());
        return "";
    }
    public String getTimeaccessFormat(){
        if(getTimeaccess() != null)
            return new SimpleDateFormat("HH:mm:ss dd.MM.yy").format(getTimeaccess());
        return "";
    }
   
}
