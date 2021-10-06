package core.util;

import core.payload.ObjectPayload;
import org.reflections.Reflections;

import java.util.Iterator;
import java.util.Set;

public class Payload {
    public static Class<?> getPayloadClass(String className) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(ObjectPayload.class.getPackage().getName() + "." + className);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (clazz != null && !ObjectPayload.class.isAssignableFrom(clazz)) {
            clazz = null;
        }
        return clazz;
    }

    public static Set<Class<? extends ObjectPayload>> getAllPayloadClass() {
        String payloadPackage = ObjectPayload.class.getPackage().getName();
        Reflections reflections = new Reflections(payloadPackage);
        return reflections.getSubTypesOf(ObjectPayload.class);
    }

}
