package core;

import core.scan.GadgetScanner;
import core.scan.KeyScanner;
import core.scan.ShiroScanner;

import java.util.HashMap;

public class Main {

    private static final HashMap<String, ShiroScanner> scanModule = new HashMap<>();

    static {
        scanModule.put("key", new KeyScanner());
        scanModule.put("gadget", new GadgetScanner());
    }

    public static void main(String[] args) throws Exception {
//        String module = args[0];
        String module = "gadget";
        String base = "http://127.0.0.1:8000/login.jsp";
        ShiroScanner scanner = scanModule.get(module);
        scanner.setBase(base);
        scanner.scan();
    }
}
