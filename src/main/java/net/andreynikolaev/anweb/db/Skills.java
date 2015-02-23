package net.andreynikolaev.anweb.db;

import net.andreynikolaev.anweb.db.auto._Skills;
import org.apache.cayenne.validation.SimpleValidationFailure;
import org.apache.cayenne.validation.ValidationResult;

public class Skills extends _Skills {
    
    public Skills(){
        
    }
    
    public Skills(boolean b){
        
        setName("");
        setRating(0);
        setShow(b);
        setShowPosition(0);
        
    }

    public Skills(Skills s) {
        
        this(true);
        setName(s.getName());
        setRating(s.getRating());
        setShow(s.getShow());
        setShowPosition(s.getShowPosition());
        
        
    }
    
    @Override
    public void validateForSave(ValidationResult validationResult) {
        super.validateForSave(validationResult);
        if(getSkillsGroup().getSkills().size() > 100) {//необхрдимо ограничит кол-во выводимых ошибок одной
            validationResult.addFailure(new SimpleValidationFailure(this, "Too many skills in group"));  
          }
        if (getShowPosition() < 0 ) {
            validationResult.addFailure(new SimpleValidationFailure(this, "Show position must to be positiv"));
        }
        if (getName().length() == 0){
         validationResult.addFailure(new SimpleValidationFailure(this, "Name of skill must not to be blank"));  
      }
   }

}
