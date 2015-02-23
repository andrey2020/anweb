/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.db.serializewrapper;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.andreynikolaev.anweb.db.LangList;
import net.andreynikolaev.anweb.db.Profiles;
import net.andreynikolaev.anweb.dbutil.IdGenerator;
import org.apache.cayenne.BaseContext;
import org.apache.cayenne.Cayenne;
import org.apache.cayenne.CayenneContext;

/**
 *
 * @author andrey
 */
@XStreamAlias("Profile")
public final class ProfilesSerialize{
    
    String address;
    Date birthDay;
    String bodyColor;
    String city;
    String email;
    String mainColor;
    Boolean mapIsSHow;
    String password;
    String phone;
    byte[] photo;
    String photoS;
    String photoX;
    String photoY;
    private String profileName;
    String secondColor;
    Boolean superAdmin;
    String web;
    String zipCode;
    int defLang;
    
    @XStreamImplicit
    @XStreamAlias("ProfileDetails")
    List<ProfileDetailsSerialize> profileDetails = new ArrayList<>();

    



    public void getCayenneDataObject(Profiles profile) {

        
        profile.setAddress(address);
        profile.setBirthDay(birthDay);
        profile.setBodyColor(bodyColor);
        profile.setCity(city);
        profile.setEmail(email);
        
        profile.setMainColor(mainColor);
        profile.setMapIsShow(mapIsSHow);
        
        profile.setPhone(phone);
        profile.setPhoto(photo);
        profile.setPhotoS(photoS);
        profile.setPhotoX(photoX);
        profile.setPhotoY(photoY);
        
        profile.setSecondColor(secondColor);
        
        profile.setWeb(web);
        profile.setZipCode(zipCode);
        
        profile.getObjectContext().deleteObjects(profile.getProfileDetails());

        
        profileDetails.stream().forEach(pd ->{
            profile.addToProfileDetails(pd.toCayenneDataObject(profile));
        });
        
        profile.setDefLang((LangList) Cayenne.objectForPK(BaseContext.getThreadObjectContext(), LangList.class, this.defLang));

    }


    public ProfilesSerialize(Profiles profile) {
        this.address = profile.getAddress();
        this.birthDay = profile.getBirthDay();
        this.bodyColor = profile.getBodyColor();
        this.city = profile.getCity();
        this.defLang = profile.getDefLang().getID();
        this.email = profile.getEmail();
        this.mainColor = profile.getMainColor();
        this.mapIsSHow = profile.getMapIsShow();
        this.password = "SECRET";
        this.phone = profile.getPhone();
        this.photo = profile.getPhoto();
        this.photoS = profile.getPhotoS();
        this.photoX = profile.getPhotoX();
        this.photoY = profile.getPhotoY();
        this.profileName = profile.getProfileName();
        this.secondColor = profile.getSecondColor();
        this.superAdmin = false;
        this.web = profile.getWeb();
        this.zipCode = profile.getZipCode();
        
        profile.getProfileDetails().stream().forEach(pd -> {
            this.profileDetails.add(new ProfileDetailsSerialize(pd));
        });
        
    }

    public String getProfileName() {
        return profileName;
    }
    
}
