/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.db.serializewrapper;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.Date;
import net.andreynikolaev.anweb.db.Experience;

/**
 *
 * @author andrey
 */
@XStreamAlias("Experiences")
public class ExperienceSerialize{
    
    String buisnessName;
    String description;
    Date endDate;
    String jobTitle;
    Boolean show;
    Date startDate;


    public Experience toCayenneDataObject() {
        Experience experience =  new Experience();
        experience.setBusinesName(buisnessName);
        experience.setDescription(description);
        experience.setEndDate(endDate);
        experience.setJobTitle(jobTitle);
        experience.setShow(show);
        experience.setStartDate(startDate);
        
        return experience;
    }


    public ExperienceSerialize(Experience object) {
        this.buisnessName = object.getBusinesName();
        this.description = object.getDescription();
        this.endDate = object.getEndDate();
        this.jobTitle = object.getJobTitle();
        this.show = object.getShow();
        this.startDate = object.getStartDate();
    }
    
}
