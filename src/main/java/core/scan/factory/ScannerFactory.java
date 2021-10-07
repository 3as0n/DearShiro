package core.scan.factory;

import core.scan.*;
import core.scan.eneity.ShiroTarget;

public class ScannerFactory {
    private final ShiroTarget target;

    public ScannerFactory(ShiroTarget target) {
        this.target = target;
    }

    public ShiroScanner getScanner(String module) throws ClassNotFoundException {
        switch (module) {
            case "key":
                return new KeyScanner(target);
            case "gadgetfuzz":
                return new GadgetScanner(target);
            case "gadgetexec":
                return new GadgetExec(target);
        }
        throw new ClassNotFoundException("Module not fount, plz check again");
    }
}
