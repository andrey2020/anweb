package net.andreynikolaev.anweb.db;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toList;
import net.andreynikolaev.anweb.db.auto._Profiles;
import net.andreynikolaev.anweb.db.serializewrapper.ProfilesSerialize;
import net.andreynikolaev.anweb.db.serializewrapper.SkillsGroupSerialize;
import net.andreynikolaev.anweb.db.serializewrapper.SkillsSerialize;
import net.andreynikolaev.anweb.dbutil.IdGenerator;
import net.andreynikolaev.anweb.dbutil.ImportProfileException;
import org.apache.cayenne.query.SelectQuery;
import org.apache.cayenne.validation.SimpleValidationFailure;
import org.apache.cayenne.validation.ValidationResult;
import org.apache.commons.codec.binary.Hex;

public final class Profiles extends _Profiles{
    @SuppressWarnings("compatibility:-8553633279241804074")
    private static final long serialVersionUID = 1L;

    private String base64Img;
    
    private ProfileDetails selectedProfileDetails;
    
    private Integer bufferExperience = -1;
    private Integer bufferSkillsGroup = -1;
    
    public Profiles(){
        
    }
    
    public Profiles (String profileName, String email, String password){
        this.setEmail(email);
        this.setPassword(password);
        this.setProfileName(profileName);
        this.setSuperAdmin(false);
        this.setPhotoX("0");
        this.setPhotoY("0");
        this.setPhotoS("1");
        this.setMapIsShow(Boolean.FALSE);
        this.setMainColor("626578");
        this.setSecondColor("FFFFFF");
        this.setBodyColor("FFFFFF");
        this.setID(IdGenerator.getIdbyString(profileName));
    }
    
    @Override
    public void setDefLang(LangList defLang) {
        setToOneTarget("defLang", defLang, true);
        if(getProfileDetails().stream().filter(p -> p.getLang() == defLang).count() == 0){
            this.addToProfileDetails(new ProfileDetails(defLang, true));
        }
        getProfileDetails().stream().filter(p -> p.getLang() == defLang).findFirst().get().setShow(Boolean.TRUE);
    }
    
    public String setSelectedDetailsByLangName(String localeName) { 
        selectedProfileDetails = (ProfileDetails) getProfileDetails().stream()
                .filter(p -> (p.getShow() && p.getLang().getLocaleName().equals(localeName))).findFirst()
                .orElse(getDetailsByLang(getDefLang().getLocaleName()));

        return selectedProfileDetails.getLang().getLocaleName();
    }
    
    public List<LangList> getSupportedLang() {
        return getProfileDetails().stream()
                .filter(p -> (p.getShow()))
                .map(ProfileDetails::getLang)
                .collect(toList());
    }
    
    public List<LangList> getAllLangFromProfile() {
        return getProfileDetails().stream()
                .map(ProfileDetails::getLang)
                .collect(toList());
    }
    
    public String getProfileDefaultLang(){
        return this.getDefLang().getLocaleName();
    }
    
    public ProfileDetails getDetailsByLang(String lang){
        return getProfileDetails().stream().filter(pd -> pd.getLang().getLocaleName().equals(lang)).findFirst().get();

    }

    
    public String getMainColorSVG() {
        return "#" + getMainColor();
    }
    
    public String getSecondColorSVG() {
        return "#" + getSecondColor();
    }
    
    public String getBodyColorSVG() {
        return "#" + getBodyColor();
    }
    
    public String getBase64Img(){
        String base64ImgFromDB = (getPhoto() == null ? " " : "data:image/png;base64," + Base64.getEncoder().encodeToString(getPhoto()));
        base64Img = base64ImgFromDB;
  
        return base64Img;
    }
    
    public String getFullAddress(){
        return getZipCode() + " " + getCity();
    }
    
    public void setPhotoToNull(){
        this.setPhoto(null);
    }
    
    public void rollbackChangesLocally(){
        this.getObjectContext().rollbackChangesLocally();
    }
    
    public void rollbackChanges(){
        this.getObjectContext().rollbackChanges();
    }

    public ProfileDetails getSelectedProfileDetails() {
        return selectedProfileDetails;
       
    }

    public void setSelectedProfileDetails(ProfileDetails selectedProfileDetails) {
        this.selectedProfileDetails = selectedProfileDetails;
    }

    public boolean checkLogin(String password) {
        return this.getPassword().equals(getHash(password));
    }

    public boolean addLanguages(List<LangList> list) {
        list.stream().forEach((ll) ->{
            addToProfileDetails(new ProfileDetails(ll, true));                
        });
        return true;
    }

    public boolean deleteLanguages(List<LangList> list) {
        list.stream().forEach((ll) ->{
            getProfileDetails().stream()
                .filter(pd -> (pd.getLang().equals(ll) && !pd.getLang().equals(getDefLang())))
                .findFirst()
                .ifPresent(pd -> {
                    removeFromProfileDetails(pd);
                    getObjectContext().deleteObjects(pd);                    
                });
        });
        return true;
    }
    
    @Override
    public void setPassword(String pass){
        writeProperty("password", getHash(pass));
    }
    
    private String getHash(String str){
        String password = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            //password = digest.digest(str.getBytes("UTF-8")).toString();
            digest.update(str.getBytes(Charset.forName("UTF-8")));
            password = new String(Hex.encodeHex(digest.digest()));
            Charset.forName("UTF-8");
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Profiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return password;
    }


    
    @Override
    protected void validateForSave(ValidationResult validationResult) {
        super.validateForSave(validationResult);


        if(getEmail() == null || getEmail().length() == 0) {
             validationResult.addFailure(new SimpleValidationFailure(this, "Email format eroor."));  
          }

        if(getAddress() != null && getAddress().length() > 250) {
             validationResult.addFailure(new SimpleValidationFailure(this, "Address format error."));
          }  
    
   }

    public void save() {
        getObjectContext().commitChanges();
        getObjectContext().commitChangesToParent();
    }

    public Collection<LangList> getLangFromSysstem() {
        return getObjectContext().performQuery(new SelectQuery(LangList.class));
    }

    public ByteArrayInputStream getExportByteArrayInputStream() {
        XStream xstream = new XStream(new StaxDriver());
        xstream.processAnnotations(ProfilesSerialize.class);
        xstream.alias("SkillsGroup", SkillsGroupSerialize.class);
        xstream.alias("Skills", SkillsSerialize.class);
        return new ByteArrayInputStream(xstream.toXML(new ProfilesSerialize(this)).getBytes(StandardCharsets.UTF_8));
    }

    public void importFromInputStream(InputStream inputstream) throws ImportProfileException {

        XStream xstream = new XStream(new StaxDriver());
        xstream.processAnnotations(ProfilesSerialize.class);
        xstream.alias("SkillsGroup", SkillsGroupSerialize.class);
        xstream.alias("Skills", SkillsSerialize.class);
        Object obj = xstream.fromXML(inputstream);

        if(obj instanceof ProfilesSerialize && ((ProfilesSerialize) obj).getProfileName().equals(this.getProfileName())){
            ((ProfilesSerialize) obj).getCayenneDataObject(this);
            save();
        }else{
          throw new ImportProfileException("Import Profile Eroor!");
        }

    }
    
    public void copyExperience(Integer detailsId){
        bufferExperience = detailsId;
    }
    
    public void copySkillsGroup(Integer detailsId){
        bufferSkillsGroup = detailsId;
    }
    
    public void insertSkillsGroup(ProfileDetails detail){
        /*getProfileDetails().stream().filter(pd -> pd.getProfileDetailId() == bufferSkillsGroup)
                .findFirst()
                .ifPresent(pd -> detail.addToSkillsGroups(pd.getSkillsGroups()));
        
        bufferSkillsGroup.stream().forEach((sk) ->{
            detail.addToSkillsGroups(sk);
            sk.getCloneSkills().stream().forEach((s) ->{             
                sk.addToSkills(new Skills(s));
               
            });
            sk.setCloneSkills(null);
        });
        bufferSkillsGroup.clear();
        */
    }
    
    public void insertExperience(ProfileDetails detail){
       /* bufferExperience.stream().forEach((ex) ->{
            detail.addToExperiences(ex);
        });
        bufferExperience.clear();
        */
    }
    
    public Integer getBufferExperience() {
        return bufferExperience;
    }

    public Integer getBufferSkillsGroup() {
        return bufferSkillsGroup;
    }



    
}
