package core.scan.factory;

import core.scan.*;

public class ScannerFactory {
    private final ShiroTarget target;

    public ScannerFactory(ShiroTarget target) {
        this.target = target;
    }

    public ShiroScanner getScanner(String module) {
        switch (module) {
            case "key":
                return new KeyScanner(target);
            case "gadgetfuzz":
                return new GadgetScanner(target);
        }
        return null;
    }
}
