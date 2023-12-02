package me.rootdeibis.commonlib.command;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;

public class MethodUtils {

    @SuppressWarnings("unchecked")
    public static <T> T invokeMethod(Object instance, Method method, Object... values) {
        LinkedList<Object> parameters = new LinkedList<>();

        for (Class<?> parameterType : method.getParameterTypes()) {
          Object parameter = Arrays.stream(values).filter(parameterType::isInstance).findFirst().orElse(null);

          parameters.add(parameter);

        }

        try {
            
            Object value = method.invoke(instance, parameters.toArray());

            return (T) value;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }


    }
    @SuppressWarnings("unchecked")
    public static <T> T reference(Class<?> clazz,String name) {

         try {
            Method method = clazz.getDeclaredMethod(name);

            return (T) method.invoke(clazz);

        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
