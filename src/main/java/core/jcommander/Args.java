package core.jcommander;

import com.beust.jcommander.Parameter;

public class Args {

    @Parameter(names = {"-m", "--module"}, description = "module", required = true)
    public String module;

    @Parameter(names = {"-b", "--baseurl"}, description = "baseurl", required = true)
    public String base;

    @Parameter(names = {"-k", "--key"}, description = "key")
    public String key = "kPH+bIxk5D2deZiIxcaaaA==";

    @Parameter(names = {"-g", "--gadget"}, description = "gadget")
    public String gadget = "NoCC";

    @Parameter(names = {"-c", "--command"}, description = "command")
    public String command = "open -a Calculator";

}
