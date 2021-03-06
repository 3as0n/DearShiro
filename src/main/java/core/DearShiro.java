package core;

import com.beust.jcommander.JCommander;
import core.parse.Args;
import core.scan.*;
import core.scan.eneity.ShiroTarget;
import core.scan.factory.ScannerFactory;

public class DearShiro {

    public static void main(String[] args) throws Exception {
        Args argv = new Args();
        JCommander jCommander = JCommander.newBuilder().addObject(argv).build();
        jCommander.parse(args);

        if (argv.help) {
            jCommander.usage();
            printUsage();
            System.exit(-1);
        }

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

    private static void printUsage() {
        System.out.println("java -jar dearshiro.jar -m {module} -b {baseurl} [-k] [-g] [-c]");
        System.out.println("# key module 扫描key");
        System.out.println("java -jar dearshiro.jar -m \"key\" -b {baseurl}");
        System.out.println("# dadgetfuzz module 扫描可用gadget");
        System.out.println("java -jar dearshrio.jar -m \"gadgetfuzz\" -b {baseurl} -k {key}");
        System.out.println("# gadgetexec module 使用gadget执行任意命令");
        System.out.println("java -jar dearshiro.jar -m \"gadgetexec\" -b {baseurl} -k {key} -g {gadget} -c {command}");
    }
}
