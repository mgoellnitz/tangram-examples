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
package org.tangram.ebean.solution.test;

import java.lang.reflect.Method;
import org.junit.Assert;
import org.junit.Test;
import org.tangram.example.RootTopic;


public class EnhancerTest {

    /**
     * From time to time we ran into the problem that classes didn't get enhanced
     */
    @Test
    public void testIsEnhanced() {
        Method[] methods = RootTopic.class.getMethods();
        boolean flag = false;
        for (Method method : methods) {
            if (method.getName().startsWith("_ebean")) {
                flag = true;
            } // if
        } // for
        Assert.assertTrue("Classes not enhanced - output unusable", flag);
    } // testIsEnhanced()

} // EnhancerTest
