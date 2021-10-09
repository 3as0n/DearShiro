package core.parse;

import com.beust.jcommander.Parameter;

public class Args {

    @Parameter(names = {"-m", "--module"}, description = "module", required = true, order = 0)
    public String module;

    @Parameter(names = {"-b", "--baseurl"}, description = "baseurl", required = true, order = 1)
    public String base;

    @Parameter(names = {"-k", "--key"}, description = "key", order = 2)
    public String key = "kPH+bIxk5D2deZiIxcaaaA==";

    @Parameter(names = {"-g", "--gadget"}, description = "gadget", order = 3)
    public String gadget = "NoCC";

    @Parameter(names = {"-c", "--command"}, description = "command", order = 4)
    public String command = "open -a Calculator";

    @Parameter(names = "--help", help = true, order = 5)
    public boolean help;
}
