package org.tangram.rdbms.solution.test;

import java.lang.reflect.Method;

import org.junit.Test;
import org.springframework.util.Assert;
import org.tangram.rdbms.solution.RootTopic;

public class ExampleContentTest {

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
        Assert.isTrue(flag, "Classes not enhanced - output unusable");
    } // testIsEnhanced()

} // ExampleContentTest
