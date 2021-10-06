package core.payload;

import org.apache.shiro.subject.SimplePrincipalCollection;

public class ScanKey implements ObjectPayload{
    @Override
    public Object getObjectPayload() {
        return new SimplePrincipalCollection();
    }
}
