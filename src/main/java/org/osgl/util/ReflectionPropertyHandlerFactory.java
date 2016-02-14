package org.osgl.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Create {@link PropertyGetter} and {@link PropertySetter} based on Java reflection
 */
public class ReflectionPropertyHandlerFactory implements PropertyHandlerFactory {
    @Override
    public PropertySetter createPropertySetter(Class c, String propName) {
        PropertySetter propertySetter;
        String p = S.capFirst(propName);
        String setter = "set" + p;
        String isser = "is" + p;
        Method[] ma = c.getMethods();
        for (Method m : ma) {
            String mn = m.getName();
            if (S.neq(setter, mn) && S.neq(isser, mn) && S.neq(p, mn)) {
                continue;
            }
            Class[] ca = m.getParameterTypes();
            if (ca != null && ca.length == 1) {
                return new ReflectionPropertySetter(c, m, null);
            }
        }
        try {
            Field f = c.getDeclaredField(propName);
            f.setAccessible(true);
            return new ReflectionPropertySetter(c, null, f);
        } catch (NoSuchFieldException e) {
            throw E.unexpected("Cannot find access method to field %s on class %s", propName, c);
        }
    }

    @Override
    public PropertyGetter createPropertyGetter(Class c, String propName) {
        PropertyGetter propertyGetter;
        String p = S.capFirst(propName);
        String getter = "get" + p;
        try {
            Method m = c.getMethod(getter);
            propertyGetter = new ReflectionPropertyGetter(c, m, null);
        } catch (NoSuchMethodException e) {
            String isser = "is" + p;
            try {
                Method m = c.getMethod(isser);
                propertyGetter = new ReflectionPropertyGetter(c, m, null);
            } catch (NoSuchMethodException e1) {
                try {
                    // try jquery style getter
                    Method m = c.getMethod(propName);
                    propertyGetter = new ReflectionPropertyGetter(c, m, null);
                } catch (NoSuchMethodException e2) {
                    try {
                        Field f = c.getDeclaredField(propName);
                        f.setAccessible(true);
                        propertyGetter = new ReflectionPropertyGetter(c, null, f);
                    } catch (NoSuchFieldException e3) {
                        throw E.unexpected("Cannot find access method to field %s on class %s", propName, c);
                    }
                }
            }
        }
        return propertyGetter;
    }
}