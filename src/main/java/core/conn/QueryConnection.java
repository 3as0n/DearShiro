package core.conn;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class QueryConnection {
    private final URL address;

    public QueryConnection(String address) throws MalformedURLException {
        this.address = new URL(address);
    }

    public String query() throws IOException {
        URLConnection connection = this.address.openConnection();
        Scanner scanner = new Scanner(connection.getInputStream());
        String jsonResult = scanner.hasNextLine() ? scanner.nextLine() : "";
        JSONObject response = JSONObject.parseObject(jsonResult);

        return response.get("data").toString();
    }
}
