/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.db.serializewrapper;

import org.apache.cayenne.CayenneDataObject;

/**
 *
 * @author andrey
 * @param <T>
 */
public abstract class BaseSerializeWrapper<T extends CayenneDataObject> {

    
    public abstract T toCayenneDataObject(BaseSerializeWrapper object);
    
    public abstract BaseSerializeWrapper toSerializeObject(T object);    
    
    
}
