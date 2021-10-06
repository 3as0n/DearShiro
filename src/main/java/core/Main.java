package core;

import core.scan.GadgetExec;
import core.scan.GadgetScanner;
import core.scan.KeyScanner;
import core.scan.ShiroScanner;

import java.util.HashMap;

public class Main {

    private static final HashMap<String, ShiroScanner> scanModule = new HashMap<>();

    static {
        scanModule.put("key", new KeyScanner());
        scanModule.put("gadgetfuzz", new GadgetScanner());
        scanModule.put("gadgetexec", new GadgetExec());
    }

    public static void main(String[] args) throws Exception {
//        String module = args[0];
        String module = "key";
        String base = "http://127.0.0.1:8000/login.jsp";
        ShiroScanner scanner = scanModule.get(module);
        scanner.setBase(base);
        scanner.scan();
    }
}
