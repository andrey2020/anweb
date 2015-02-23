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
import net.andreynikolaev.anweb.db.LangList;
import net.andreynikolaev.anweb.db.ProfileDetails;
import net.andreynikolaev.anweb.db.Profiles;
import net.andreynikolaev.anweb.db.SkillsGroup;
import org.apache.cayenne.BaseContext;
import org.apache.cayenne.Cayenne;

/**
 *
 * @author andrey
 */
@XStreamAlias("ProfileDetails")
public class ProfileDetailsSerialize{
    
    String firstName;
    String info;
    String lastName;
    Boolean show;
    String title1;
    String title2;
    int lang;
    
    @XStreamImplicit
    @XStreamAlias("Experiences")
    List<ExperienceSerialize> experiences = new ArrayList<>();
    
    @XStreamImplicit
    @XStreamAlias("SkillsGroup")
    private List<SkillsGroupSerialize> skillsGroup = new ArrayList<>();

  
    public ProfileDetails toCayenneDataObject(Profiles p) {
        ProfileDetails profileDetails = new ProfileDetails();
        
        profileDetails.setFirstName(firstName);
        profileDetails.setInfo(info);
        profileDetails.setLastName(lastName);
        profileDetails.setShow(show);
        profileDetails.setTitle1(title1);
        profileDetails.setTitle2(title2);
        profileDetails.setProfile(p);
        
        
        profileDetails.setLang((LangList) Cayenne.objectForPK(BaseContext.getThreadObjectContext(), LangList.class, lang));
        
        if(experiences != null){
            experiences.stream().forEach(ex ->{
                profileDetails.addToExperiences(ex.toCayenneDataObject());
            });
        }
        if(skillsGroup != null){
            skillsGroup.stream().forEach(sg ->{
                SkillsGroup sgg = sg.toCayenneDataObject();
                profileDetails.addToSkillsGroups(sgg);
                sg.setSkills(sgg);
            });
        }
        return profileDetails;
    }


    public ProfileDetailsSerialize(ProfileDetails object) {
        this.firstName = object.getFirstName();
        this.info = object.getInfo();
        this.lang = object.getLang().getID();
        this.lastName = object.getLastName();
        this.show = object.getShow();
        this.title1 = object.getTitle1();
        this.title2 = object.getTitle2();
        

        object.getExperiences().stream().forEach(e -> {
           this.experiences.add(new ExperienceSerialize(e));
        });
        
        
        object.getSkillsGroups().stream().forEach(sk -> {
           this.skillsGroup.add(new SkillsGroupSerialize(sk));
        });
        

    }

    public List<SkillsGroupSerialize> getSkillsGroup() {
        return skillsGroup;
    }
    
}
