package core.scan;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ShiroHttpConnection {

    /**
     * 预先编译好正则，不使用String.matches(...)
     */
    private static final Pattern pattern = Pattern.compile(".*rememberMe=deleteMe.*");

    public boolean checkKey(String base, String rememberMe) throws Exception {
        // For test
        URL url = new URL(base);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // 探测key，请求方式只能为GET，使用POST请求方式获得响应头的时候会出现BUG
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36");
        connection.setRequestProperty("Cookie", "rememberMe=" + rememberMe);
        connection.setDoOutput(true);

        int responseCode = connection.getResponseCode();
        System.out.println("[-] Response Code: " + responseCode);
        Map<String, List<String>> fields = connection.getHeaderFields();
        List<String> setCookie = fields.get("Set-Cookie");

        return setCookie.stream().noneMatch(line -> pattern.matcher(line).matches());
    }
}
