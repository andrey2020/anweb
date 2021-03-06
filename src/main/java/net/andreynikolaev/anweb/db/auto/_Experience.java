package net.andreynikolaev.anweb.db.auto;

import java.util.Date;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

import net.andreynikolaev.anweb.db.ProfileDetails;

/**
 * Class _Experience was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Experience extends CayenneDataObject {

    @Deprecated
    public static final String BUSINES_NAME_PROPERTY = "businesName";
    @Deprecated
    public static final String DESCRIPTION_PROPERTY = "description";
    @Deprecated
    public static final String END_DATE_PROPERTY = "endDate";
    @Deprecated
    public static final String JOB_TITLE_PROPERTY = "jobTitle";
    @Deprecated
    public static final String SHOW_PROPERTY = "show";
    @Deprecated
    public static final String START_DATE_PROPERTY = "startDate";
    @Deprecated
    public static final String PROFILE_DETAIL_PROPERTY = "profileDetail";

    public static final String ID_PK_COLUMN = "ID";

    public static final Property<String> BUSINES_NAME = new Property<String>("businesName");
    public static final Property<String> DESCRIPTION = new Property<String>("description");
    public static final Property<Date> END_DATE = new Property<Date>("endDate");
    public static final Property<String> JOB_TITLE = new Property<String>("jobTitle");
    public static final Property<Boolean> SHOW = new Property<Boolean>("show");
    public static final Property<Date> START_DATE = new Property<Date>("startDate");
    public static final Property<ProfileDetails> PROFILE_DETAIL = new Property<ProfileDetails>("profileDetail");

    public void setBusinesName(String businesName) {
        writeProperty("businesName", businesName);
    }
    public String getBusinesName() {
        return (String)readProperty("businesName");
    }

    public void setDescription(String description) {
        writeProperty("description", description);
    }
    public String getDescription() {
        return (String)readProperty("description");
    }

    public void setEndDate(Date endDate) {
        writeProperty("endDate", endDate);
    }
    public Date getEndDate() {
        return (Date)readProperty("endDate");
    }

    public void setJobTitle(String jobTitle) {
        writeProperty("jobTitle", jobTitle);
    }
    public String getJobTitle() {
        return (String)readProperty("jobTitle");
    }

    public void setShow(Boolean show) {
        writeProperty("show", show);
    }
    public Boolean getShow() {
        return (Boolean)readProperty("show");
    }

    public void setStartDate(Date startDate) {
        writeProperty("startDate", startDate);
    }
    public Date getStartDate() {
        return (Date)readProperty("startDate");
    }

    public void setProfileDetail(ProfileDetails profileDetail) {
        setToOneTarget("profileDetail", profileDetail, true);
    }

    public ProfileDetails getProfileDetail() {
        return (ProfileDetails)readProperty("profileDetail");
    }


}
