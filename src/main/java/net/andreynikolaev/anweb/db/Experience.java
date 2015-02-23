package net.andreynikolaev.anweb.db;

import java.text.SimpleDateFormat;
import java.util.Date;
import net.andreynikolaev.anweb.db.auto._Experience;
import org.apache.cayenne.validation.SimpleValidationFailure;
import org.apache.cayenne.validation.ValidationResult;

public class Experience extends _Experience {
    
    public Experience() {

    }
    
    Experience(boolean b) {
        setBusinesName("");
        setJobTitle("");
        setShow(true);
        setStartDate(new Date());
        setDescription("");
    }

    public Experience(Experience ex) {
        setBusinesName(ex.getBusinesName());
        setJobTitle(ex.getJobTitle());
        setShow(ex.getShow());
        setStartDate(ex.getStartDate());
        setEndDate(ex.getEndDate());
        setDescription(ex.getDescription());
    }
    
    public String getEndDateFormat(){
        if(getEndDate() != null)
            return new SimpleDateFormat("MMM. yyyy").format(getEndDate());
        else
        return "";
    }
    
    public String getStartDateFormat(){
        if(getStartDate() != null)
            return new SimpleDateFormat("MMM. yyyy").format(getStartDate());
        return "";
    }
    
        
    @Override
    public void validateForSave(ValidationResult validationResult) {
        super.validateForSave(validationResult);
        if(getProfileDetail().getExperiences().size() > 100) {//необхрдимо ограничит кол-во выводимых ошибок одной
            validationResult.addFailure(new SimpleValidationFailure(this, "Too many expiriences entries"));  
        }
        if (getJobTitle().length() == 0){
            validationResult.addFailure(new SimpleValidationFailure(this, "Job title must not to be blank"));  
        }
        if (getBusinesName().length() == 0){
            validationResult.addFailure(new SimpleValidationFailure(this, "Job title must not to be blank"));  
        }    
        if (getDescription().length() > 1024){
            validationResult.addFailure(new SimpleValidationFailure(this, "Description must to be less as 1024"));  
        }
        if (getStartDate().toString().length() == 0){
            validationResult.addFailure(new SimpleValidationFailure(this, "Start date must not to be blank"));  
        }
    }

}
