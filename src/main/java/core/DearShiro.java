package core;

import com.beust.jcommander.JCommander;
import core.jcommander.Args;
import core.scan.*;
import core.scan.eneity.ShiroTarget;
import core.scan.factory.ScannerFactory;

public class DearShiro {

    public static void main(String[] args) throws Exception {
//        args = new String[]{"-m", "gadgetfuzz", "-b", "http://127.0.0.1:8000/login.jsp"};
        Args argv = new Args();
        JCommander.newBuilder().addObject(argv).build().parse(args);
        String module = argv.module;
        String base = argv.base;
        String key = argv.key;
        String gadget = argv.gadget;
        String command = argv.command;

        ShiroTarget target = new ShiroTarget(base, key, gadget, command);
        ScannerFactory factory = new ScannerFactory(target);
        ShiroScanner scanner = factory.getScanner(module);
        scanner.scan();
    }

    private void printUsage() {

    }
}
