import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.media.sound.SoftTuning;
import core.conn.QueryConnection;
import core.payload.ObjectPayload;
import core.util.ConfigPropertiesReader;
import jdk.nashorn.internal.parser.JSONParser;

import java.io.InputStream;
import java.util.Scanner;
import java.util.UUID;

public class Test {
    public static void main(String[] args) throws Exception {
//        String add = "http://api.ceye.io/v1/records?token=9e74c587b88cd03005d6150e90025a70&type=http&filter=bbb";
//        QueryConnection connection = new QueryConnection(add);
//        System.out.println(connection.query());

        System.out.println(ConfigPropertiesReader.getProp("ceye.token"));
        System.out.println(ConfigPropertiesReader.getProp("ceye.identifier"));
    }
}
