/**
 * 
 * Copyright 2012 Martin Goellnitz
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.tangram.gae.solution.test;

import java.lang.reflect.Method;

import org.junit.Test;
import org.springframework.util.Assert;
import org.tangram.gae.solution.RootTopic;

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
