package net.andreynikolaev.anweb.db.auto;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

/**
 * Class _I18nSystem was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _I18nSystem extends CayenneDataObject {

    @Deprecated
    public static final String ID_PROPERTY = "ID";
    @Deprecated
    public static final String I18N_KEY_PROPERTY = "i18nKey";
    @Deprecated
    public static final String I18N_LOCALE_PROPERTY = "i18nLocale";
    @Deprecated
    public static final String I18N_VALUE_PROPERTY = "i18nValue";
    @Deprecated
    public static final String SHOW_PROPERTY = "show";

    public static final String ID_PK_COLUMN = "ID";

    public static final Property<Integer> ID = new Property<Integer>("ID");
    public static final Property<String> I18N_KEY = new Property<String>("i18nKey");
    public static final Property<String> I18N_LOCALE = new Property<String>("i18nLocale");
    public static final Property<String> I18N_VALUE = new Property<String>("i18nValue");
    public static final Property<Boolean> SHOW = new Property<Boolean>("show");

    public void setID(Integer ID) {
        writeProperty("ID", ID);
    }
    public Integer getID() {
        return (Integer)readProperty("ID");
    }

    public void setI18nKey(String i18nKey) {
        writeProperty("i18nKey", i18nKey);
    }
    public String getI18nKey() {
        return (String)readProperty("i18nKey");
    }

    public void setI18nLocale(String i18nLocale) {
        writeProperty("i18nLocale", i18nLocale);
    }
    public String getI18nLocale() {
        return (String)readProperty("i18nLocale");
    }

    public void setI18nValue(String i18nValue) {
        writeProperty("i18nValue", i18nValue);
    }
    public String getI18nValue() {
        return (String)readProperty("i18nValue");
    }

    public void setShow(Boolean show) {
        writeProperty("show", show);
    }
    public Boolean getShow() {
        return (Boolean)readProperty("show");
    }

}
