package core.scan.eneity;

public class ShiroTarget {
    /**
     * necessary
     */
    private final String base;

    private final String key;

    private final String gadget;

    private final String command;


    public ShiroTarget(String base, String key, String gadget, String command) {
        this.base = base;
        this.key = key;
        this.gadget = gadget;
        this.command = command;
    }

    public String getBase() {
        return base;
    }

    public String getKey() {
        return key;
    }

    public String getGadget() {
        return gadget;
    }

    public String getCommand() {
        return command;
    }
}
