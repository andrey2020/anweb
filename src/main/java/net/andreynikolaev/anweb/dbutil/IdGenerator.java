/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.dbutil;

import org.apache.cayenne.util.HashCodeBuilder;

/**
 *
 * @author andrey
 */
public final class IdGenerator {
    public static int getIdbyString(String value) {
        return new HashCodeBuilder(17, 37).append(value).toHashCode();
    }
}
