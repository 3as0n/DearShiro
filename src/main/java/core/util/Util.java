package core.util;

import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.util.ByteSource;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Util {
    public byte[] serialize(Object object) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)
        ) {
            oos.writeObject(object);
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

//    public String base64Encode(byte[] bytes) {
//        BASE64Encoder base64Encoder = new BASE64Encoder();
//        String payload = base64Encoder.encode(bytes);
//        return payload.replace("\n\n", "");
//    }

    public String getRememberMe(byte[] bytes, String key) {
        try {
            byte[] AES_KEY = new BASE64Decoder().decodeBuffer(key);
            AesCipherService aesCipherService = new AesCipherService();
            ByteSource source = aesCipherService.encrypt(bytes, AES_KEY);
            return source.toBase64().replace("\n\n", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
