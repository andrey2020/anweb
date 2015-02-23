package net.andreynikolaev.anweb.db;

import java.util.ArrayList;
import java.util.List;
import net.andreynikolaev.anweb.db.auto._SkillsGroup;
import org.apache.cayenne.validation.SimpleValidationFailure;
import org.apache.cayenne.validation.ValidationResult;

public class SkillsGroup extends _SkillsGroup {
    
    private List<Skills> cloneSkills;
    
    public SkillsGroup(){
        
    }
    
    public SkillsGroup cloneSkillsGroup(SkillsGroup sk){
        SkillsGroup bsg = new SkillsGroup(sk);
        

        return bsg;
    }

    public SkillsGroup(SkillsGroup sk) {
        setGroupName(sk.getGroupName());
        setShow(sk.getShow());
        setShowPosition(sk.getShowPosition());
        setCloneSkills(sk.getSkills());
        
    }
    
    public int getId(){
        return (int) this.getObjectId().getIdSnapshot().get("ID");
    }
    
    public SkillsGroup(boolean b) {
        setGroupName("");
        setShow(b);
        setShowPosition(0);
        
    }
    
    public void newSkill(){
        addToSkills(new Skills(true));
    }

    public List<Skills> getCloneSkills() {
        return cloneSkills;
    }

    public void setCloneSkills(List<Skills> cloneSkills) {
        this.cloneSkills = cloneSkills;
    }
    
    public void removeSkill(Skills sk){
        removeFromSkills(sk);
        getObjectContext().deleteObjects(sk);
    }
    
    @Override
    public void validateForSave(ValidationResult validationResult) {
        super.validateForSave(validationResult);
        if(getProfileDetail().getSkillsGroups().size() > 20) {//необхрдимо ограничит кол-во выводимых ошибок одной
            validationResult.addFailure(new SimpleValidationFailure(this, "Too many skills groups"));  
          }
        if (getShowPosition() < 0 ) {
            validationResult.addFailure(new SimpleValidationFailure(this, "Show position must to be positiv"));
        }
        if (getGroupName().length() == 0){
         validationResult.addFailure(new SimpleValidationFailure(this, "Name of skill group must not to be blank"));  
       }
   }
    
}
