package core.scan;

/**
 * To exec command
 * Need: base, key, command, gadget
 */
public class GadgetExec extends AbstractGadgetExec implements ShiroScanner {

    private String base;

    private String key;

    private String command;

    private String gadget;


    public GadgetExec(String base, String key, String command) {
        this.base = base;
        this.key = key;
        this.command = command;
    }


    @Override
    public void scan() throws Exception {
        this.exec(command);
    }

    @Override
    public void exec(String command) {
        System.out.println(command);
    }
}
