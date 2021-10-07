import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.media.sound.SoftTuning;
import core.payload.ObjectPayload;

import java.io.InputStream;
import java.util.Scanner;
import java.util.UUID;

public class Test {
    public static void main(String[] args) throws Exception {
        String json = "{\"meta\": {\"code\": 200, \"message\": \"OK\"}, \"data\": []}";
        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(jsonObject.get("data").toString().equals("[]"));
    }
}
