package core.scan.factory;

import core.scan.GadgetExec;
import core.scan.GadgetScanner;
import core.scan.KeyScanner;
import core.scan.ShiroScanner;

public class ScannerFactory {
    public ShiroScanner getScanner(String module, String base, String key, String command) {
        switch (module) {
            case "key":
                return new KeyScanner(base);
            case "gadgetfuzz":
                return new GadgetScanner(base, key);
            case "gadgetexec":
                return new GadgetExec(base, key, command);
        }
        return null;
    }
}
