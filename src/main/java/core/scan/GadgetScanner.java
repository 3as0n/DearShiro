package core.scan;

/**
 * To fuzz gadget
 */
public class GadgetScanner implements ShiroScanner{

    private String base;

    @Override
    public void setBase(String base) {
        this.base = base;
    }

    @Override
    public void scan() throws Exception {
        //TODO: 获取所有可用Gadget
    }
}
