package core.payload;

import org.apache.shiro.subject.SimplePrincipalCollection;

public class ScanKey implements ObjectPayload{
    @Override
    public Object getObjectPayload(String command) {
        return new SimplePrincipalCollection();
    }
}
