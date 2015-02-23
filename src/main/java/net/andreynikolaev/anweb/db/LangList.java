package net.andreynikolaev.anweb.db;

import net.andreynikolaev.anweb.db.auto._LangList;
import net.andreynikolaev.anweb.dbutil.IdGenerator;

public class LangList extends _LangList {
    
    public LangList(){
        
    }

    public LangList(boolean a){
        this.setLangNativeName("");        
        this.setLocaleName("");
        //this.setID(IdGenerator.getIdbyString(""));
    }
    
    public LangList(String nativeName, String localeName) {
        this.setLangNativeName(nativeName);        
        this.setLocaleName(localeName);
        this.setID(IdGenerator.getIdbyString(localeName));
    }

}
