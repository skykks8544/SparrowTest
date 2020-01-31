package com.fasoo.syn.basic;

import java.io.ObjectStreamField;
import java.io.Serializable;
import java.util.List;

/**
 * Writer: 
 * Date: 4/9/12
 */
public class INCORRECT_SERIAL_PERSISTENT_FIELDS_MODIFIER_TestCase implements Serializable {
    public ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("myField", List.class)}; /* BUG */

    private static final int [] test = {1,2,3};
    public void doSomething() {}
}
