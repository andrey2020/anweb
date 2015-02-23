package net.andreynikolaev.anweb.dbutil;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.apache.cayenne.CayenneDataObject;

/**
 *
 * @author andrey
 * @param <E>
 */
public abstract class EntityController<E extends CayenneDataObject> implements Serializable{
    
    private final String entityName;
    
    {
       entityName = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
    }
    
    protected static final String COLUM_TYPE_INT = "int";
    protected static final String COLUM_TYPE_STRING = "str";
    protected static final String COLUM_TYPE_TEXT = "text";
    protected static final String COLUM_TYPE_DATE = "date";
    protected static final String COLUM_TYPE_BOOL = "bool";
    protected static final String COLUM_TYPE_RATING = "rating";
    
    protected boolean editable;
    
    protected boolean systemTable;
    
    protected static final long serialVersionUID = 1L;

    protected E selectedEntity;
    
    protected List<E> entityList;
    
    protected List<ColumnModel> columns = new ArrayList<>();
    
    @PostConstruct
    public void formGen(){
      // columns.add(new ColumnModel("id",COLUM_TYPE_STRING,false,true,true));
       populateColumns();
    }
    
    public List<ColumnModel> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnModel> columns) {
        this.columns = columns;
    }
    
    /**
     * Delete selectedEntity Entity
     * @param id
     */
    public void deleteSelectedEntity(Integer id){
        setSelectedEntity(id);
        getEntityService().deleteEntity(selectedEntity);
        selectedEntity = null;
        entityList = null;    
    }
    
    /**
     * Clean list Entity
     */
    public void deleteAllEntity(){
        getEntityService().deleteAllEntity();
        selectedEntity = null;
        entityList = null;
    }
    
    /**
     * Set selectedEntity Entity with ID
     * @param id
     */
    public void setSelectedEntity(Object id){
        selectedEntity = (E) getEntityService().getEntityById(id);
    }
    
    public E getSelectedEntity() {
        return selectedEntity;
    }
    
    public void setSelectedEntity(){
        try {
            selectedEntity = (E) ((Class)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(EntityController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<E> getEntityList() {
        if(entityList != null && testEqual()){
            return entityList;
        }else{
            entityList = new ArrayList<>();
            entityList.addAll((Collection<E>) getEntityService().getEntityList());
            return entityList;
        }
    }
    
    public boolean testEqual(){
         return (((getEntityService().getEntityList().containsAll(entityList)) && entityList.containsAll(getEntityService().getEntityList())));
    }

    public void setEntityList(List<E> entityList) {
        this.entityList = (List<E>) entityList;
    }
    
    public boolean isEditable(){
        return editable;
    }
    
    public String getEntityName(){
        return entityName;
    }
    
    public boolean isSystemTable() {
        return systemTable;
    }

    public void setSystemTable(boolean systemTable) {
        this.systemTable = systemTable;
    }
    
    public void save(){
        System.out.println("SAVE");
        getEntityService().save();
    }
    
    abstract public EntityService getEntityService();

    abstract public void setEntityService(EntityService entityService);

    abstract public void populateColumns();

    static public class ColumnModel implements Serializable {
        private final String header;
        private final String property;
        private final String componentType;
        private final boolean editable;
        private final boolean showInTable;
        private final boolean showInDialog;

        
        public ColumnModel(String property, String componentType, boolean editable, boolean showInTable, boolean showInDialog) {
            this.header = property.toLowerCase();
            this.property = property;
            this.componentType = componentType;
            this.editable = editable;
            this.showInTable = showInTable;
            this.showInDialog = showInDialog;
        }


        public String getHeader() {
            return header;
        }

        public String getProperty() {
            return property;
        }

        public String getComponentType() {
            return componentType;
        }

        public boolean isEditable() {
            return editable;
        }

        public boolean isShowInTable() {
            return showInTable;
        }
        
        public boolean isShowInDialog() {
            return showInDialog;
        }

    }
    
}
