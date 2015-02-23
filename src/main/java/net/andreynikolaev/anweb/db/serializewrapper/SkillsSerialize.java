/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.db.serializewrapper;

import net.andreynikolaev.anweb.db.Skills;
import net.andreynikolaev.anweb.db.SkillsGroup;

/**
 *
 * @author andrey
 */
public class SkillsSerialize{
    
    String name;
    Integer rating;
    Boolean show;
    Integer showPosition;

    public void toCayenneDataObject(SkillsGroup sg) {
        Skills skills = new Skills();
        
        skills.setSkillsGroup(sg);
        skills.setName(name);
        skills.setRating(rating);
        skills.setShow(show);
        skills.setShowPosition(showPosition);
        
        sg.addToSkills(skills);
    }

    public SkillsSerialize(Skills object) {
        this.name = object.getName();
        this.rating = object.getRating();
        this.show = object.getShow();
        this.showPosition = object.getShowPosition();

    }
    
}
