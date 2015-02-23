package net.andreynikolaev.anweb.db;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import net.andreynikolaev.anweb.db.auto._ProfileDetails;
import org.apache.cayenne.CayenneDataObject;

public class ProfileDetails extends _ProfileDetails {
    
    private Integer profileDetailId;
    
    public List<Experience> getExperiencesSort() {
        return ((List<Experience>)readProperty("experiences")).stream()
                .sorted((ex1, ex2) -> ex2.getStartDate().compareTo(ex1.getStartDate()))
                .collect(Collectors.toList());
    }

    public Integer getProfileDetailId() {
        profileDetailId = Integer.valueOf(String.valueOf(this.getObjectId().getIdSnapshot().get("ID"))); 
        return profileDetailId;
    }

    public void setProfileDetailId(Integer profileId) {
        this.profileDetailId = profileId;
    }

    public ProfileDetails() {
        
    }
    
    public ProfileDetails(LangList ls, boolean isDefault) {
        setLang(ls);
        setShow(isDefault);
        
    }
    
    private String classToPropertyName(String propertyName){
       char first = Character.toUpperCase(propertyName.charAt(0));
       return first + propertyName.substring(1);
    }
    
        
    public List<? extends CayenneDataObject> getArrayEntity(String e){        
        return (List<? extends CayenneDataObject>) this.readProperty(classToPropertyName(e));
    }
    
    public void addToArrayEntity(String e, CayenneDataObject entity){
        addToManyTarget(classToPropertyName(e), entity, true);
        
    }
    
    public void removeFromArrayEntity(String e, CayenneDataObject entity){
        removeToManyTarget(classToPropertyName(e), entity, true);
        
    }
    
    public void newExperience(){
        addToExperiences(new Experience(true));
    }
    
    public void removeExperience(Experience exp){
        removeFromExperiences(exp);
        getObjectContext().deleteObjects(exp);
    }
    
    public void removeAllExperience(){
        getObjectContext().deleteObjects(getExperiences());
        getExperiences().forEach((ex) ->{
            removeFromExperiences(ex);
        });
        
    }
    
    public void removeAllSkillsGroup(){
        getObjectContext().deleteObjects(getSkillsGroups());
        getSkillsGroups().forEach((sk) ->{
            removeFromSkillsGroups(sk);
        });
    }
    
    public void removeSkillGroup(SkillsGroup skillG){
        removeFromSkillsGroups(skillG);
        getObjectContext().deleteObjects(skillG);
    }
    
    public void newSkillGroup(){
        addToSkillsGroups(new SkillsGroup(true));
    }
    
    public String getFullName(){
        
        return (super.getFirstName() == null ? " " : super.getFirstName()) + " " + (super.getLastName()== null ? " " : super.getLastName());
    }

}
