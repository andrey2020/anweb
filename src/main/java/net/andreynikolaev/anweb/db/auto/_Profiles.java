package net.andreynikolaev.anweb.db.auto;

import java.util.Date;
import java.util.List;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

import net.andreynikolaev.anweb.db.LangList;
import net.andreynikolaev.anweb.db.ProfileDetails;

/**
 * Class _Profiles was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Profiles extends CayenneDataObject {

    @Deprecated
    public static final String ID_PROPERTY = "ID";
    @Deprecated
    public static final String ADDRESS_PROPERTY = "address";
    @Deprecated
    public static final String BIRTH_DAY_PROPERTY = "birthDay";
    @Deprecated
    public static final String BODY_COLOR_PROPERTY = "bodyColor";
    @Deprecated
    public static final String CITY_PROPERTY = "city";
    @Deprecated
    public static final String EMAIL_PROPERTY = "email";
    @Deprecated
    public static final String MAIN_COLOR_PROPERTY = "mainColor";
    @Deprecated
    public static final String MAP_IS_SHOW_PROPERTY = "mapIsShow";
    @Deprecated
    public static final String PASSWORD_PROPERTY = "password";
    @Deprecated
    public static final String PHONE_PROPERTY = "phone";
    @Deprecated
    public static final String PHOTO_PROPERTY = "photo";
    @Deprecated
    public static final String PHOTO_S_PROPERTY = "photoS";
    @Deprecated
    public static final String PHOTO_X_PROPERTY = "photoX";
    @Deprecated
    public static final String PHOTO_Y_PROPERTY = "photoY";
    @Deprecated
    public static final String PROFILE_NAME_PROPERTY = "profileName";
    @Deprecated
    public static final String SECOND_COLOR_PROPERTY = "secondColor";
    @Deprecated
    public static final String SUPER_ADMIN_PROPERTY = "superAdmin";
    @Deprecated
    public static final String WEB_PROPERTY = "web";
    @Deprecated
    public static final String ZIP_CODE_PROPERTY = "zipCode";
    @Deprecated
    public static final String DEF_LANG_PROPERTY = "defLang";
    @Deprecated
    public static final String PROFILE_DETAILS_PROPERTY = "profileDetails";

    public static final String ID_PK_COLUMN = "ID";

    public static final Property<Integer> ID = new Property<Integer>("ID");
    public static final Property<String> ADDRESS = new Property<String>("address");
    public static final Property<Date> BIRTH_DAY = new Property<Date>("birthDay");
    public static final Property<String> BODY_COLOR = new Property<String>("bodyColor");
    public static final Property<String> CITY = new Property<String>("city");
    public static final Property<String> EMAIL = new Property<String>("email");
    public static final Property<String> MAIN_COLOR = new Property<String>("mainColor");
    public static final Property<Boolean> MAP_IS_SHOW = new Property<Boolean>("mapIsShow");
    public static final Property<String> PASSWORD = new Property<String>("password");
    public static final Property<String> PHONE = new Property<String>("phone");
    public static final Property<byte[]> PHOTO = new Property<byte[]>("photo");
    public static final Property<String> PHOTO_S = new Property<String>("photoS");
    public static final Property<String> PHOTO_X = new Property<String>("photoX");
    public static final Property<String> PHOTO_Y = new Property<String>("photoY");
    public static final Property<String> PROFILE_NAME = new Property<String>("profileName");
    public static final Property<String> SECOND_COLOR = new Property<String>("secondColor");
    public static final Property<Boolean> SUPER_ADMIN = new Property<Boolean>("superAdmin");
    public static final Property<String> WEB = new Property<String>("web");
    public static final Property<String> ZIP_CODE = new Property<String>("zipCode");
    public static final Property<LangList> DEF_LANG = new Property<LangList>("defLang");
    public static final Property<List<ProfileDetails>> PROFILE_DETAILS = new Property<List<ProfileDetails>>("profileDetails");

    public void setID(Integer ID) {
        writeProperty("ID", ID);
    }
    public Integer getID() {
        return (Integer)readProperty("ID");
    }

    public void setAddress(String address) {
        writeProperty("address", address);
    }
    public String getAddress() {
        return (String)readProperty("address");
    }

    public void setBirthDay(Date birthDay) {
        writeProperty("birthDay", birthDay);
    }
    public Date getBirthDay() {
        return (Date)readProperty("birthDay");
    }

    public void setBodyColor(String bodyColor) {
        writeProperty("bodyColor", bodyColor);
    }
    public String getBodyColor() {
        return (String)readProperty("bodyColor");
    }

    public void setCity(String city) {
        writeProperty("city", city);
    }
    public String getCity() {
        return (String)readProperty("city");
    }

    public void setEmail(String email) {
        writeProperty("email", email);
    }
    public String getEmail() {
        return (String)readProperty("email");
    }

    public void setMainColor(String mainColor) {
        writeProperty("mainColor", mainColor);
    }
    public String getMainColor() {
        return (String)readProperty("mainColor");
    }

    public void setMapIsShow(Boolean mapIsShow) {
        writeProperty("mapIsShow", mapIsShow);
    }
    public Boolean getMapIsShow() {
        return (Boolean)readProperty("mapIsShow");
    }

    public void setPassword(String password) {
        writeProperty("password", password);
    }
    public String getPassword() {
        return (String)readProperty("password");
    }

    public void setPhone(String phone) {
        writeProperty("phone", phone);
    }
    public String getPhone() {
        return (String)readProperty("phone");
    }

    public void setPhoto(byte[] photo) {
        writeProperty("photo", photo);
    }
    public byte[] getPhoto() {
        return (byte[])readProperty("photo");
    }

    public void setPhotoS(String photoS) {
        writeProperty("photoS", photoS);
    }
    public String getPhotoS() {
        return (String)readProperty("photoS");
    }

    public void setPhotoX(String photoX) {
        writeProperty("photoX", photoX);
    }
    public String getPhotoX() {
        return (String)readProperty("photoX");
    }

    public void setPhotoY(String photoY) {
        writeProperty("photoY", photoY);
    }
    public String getPhotoY() {
        return (String)readProperty("photoY");
    }

    public void setProfileName(String profileName) {
        writeProperty("profileName", profileName);
    }
    public String getProfileName() {
        return (String)readProperty("profileName");
    }

    public void setSecondColor(String secondColor) {
        writeProperty("secondColor", secondColor);
    }
    public String getSecondColor() {
        return (String)readProperty("secondColor");
    }

    public void setSuperAdmin(Boolean superAdmin) {
        writeProperty("superAdmin", superAdmin);
    }
    public Boolean getSuperAdmin() {
        return (Boolean)readProperty("superAdmin");
    }

    public void setWeb(String web) {
        writeProperty("web", web);
    }
    public String getWeb() {
        return (String)readProperty("web");
    }

    public void setZipCode(String zipCode) {
        writeProperty("zipCode", zipCode);
    }
    public String getZipCode() {
        return (String)readProperty("zipCode");
    }

    public void setDefLang(LangList defLang) {
        setToOneTarget("defLang", defLang, true);
    }

    public LangList getDefLang() {
        return (LangList)readProperty("defLang");
    }


    public void addToProfileDetails(ProfileDetails obj) {
        addToManyTarget("profileDetails", obj, true);
    }
    public void removeFromProfileDetails(ProfileDetails obj) {
        removeToManyTarget("profileDetails", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<ProfileDetails> getProfileDetails() {
        return (List<ProfileDetails>)readProperty("profileDetails");
    }


}
