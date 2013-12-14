/**
 * 
 * Copyright (C) 2011-2013 Martin Goellnitz
 * 
 * This work is licensed under the Creative Commons Attribution 3.0 
 * Unported License. To view a copy of this license, visit 
 * http://creativecommons.org/licenses/by/3.0/ or send a letter to 
 * Creative Commons, 444 Castro Street, Suite 900, Mountain View, 
 * California, 94041, USA.
 * 
 */
package org.tangram.mongo.solution.test;

import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.Assert;
import org.tangram.mongo.solution.RootTopic;

public class EnhancerTest {

    /**
     * From time to time we ran into the problem that classes didn't get enhanced
     */
    @Test
    public void testIsEnhanced() {
        Method[] methods = RootTopic.class.getMethods();
        boolean flag = false;
        for (Method method : methods) {
            System.out.println(""+method.getName());
            if (method.getName().startsWith("jdo")) {
                flag = true;
            } // if
        } // for
        Assert.assertTrue("Classes not enhanced - output unusable", flag);
    } // testIsEnhanced()

} // EnhancerTest
