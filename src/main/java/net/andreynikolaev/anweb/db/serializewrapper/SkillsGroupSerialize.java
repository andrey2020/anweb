/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.db.serializewrapper;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;
import java.util.List;
import net.andreynikolaev.anweb.db.SkillsGroup;
import org.apache.cayenne.ObjectContext;

/**
 *
 * @author andrey
 */
public class SkillsGroupSerialize {
    
    String groupName;
    Boolean show;
    Integer showPosition;
    
    @XStreamImplicit
    @XStreamAlias("Skills")
    List<SkillsSerialize> skills = new ArrayList<>();
    

    public SkillsGroup toCayenneDataObject() {
        SkillsGroup skillsGroup = new SkillsGroup();
        skillsGroup.setGroupName(groupName);
        skillsGroup.setShow(show);
        skillsGroup.setShowPosition(showPosition);
        return skillsGroup;
    }
    
    public void setSkills(SkillsGroup sg){
        if(skills != null){
            skills.stream().forEach(s ->{
                s.toCayenneDataObject(sg);
            });
        }
    }


    public SkillsGroupSerialize(SkillsGroup object) {
        this.groupName = object.getGroupName();
        this.show = object.getShow();
        this.showPosition = object.getShowPosition();
        
        object.getSkills().stream().forEach(sk ->{
            this.skills.add(new SkillsSerialize(sk));
        });
        
    }
    
}
