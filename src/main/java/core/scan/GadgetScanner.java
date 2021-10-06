package core.scan;

public class GadgetScanner implements ShiroScanner{

    private String base;

    @Override
    public void setBase(String base) {
        this.base = base;
    }

    @Override
    public void scan() throws Exception {
        System.out.println("Gadget");
    }
}
