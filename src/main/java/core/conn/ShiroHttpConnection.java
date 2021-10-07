package core.conn;

import com.sun.media.sound.SoftTuning;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ShiroHttpConnection {

    /**
     * 预先编译好正则节省资源，不使用String.matches(...)
     */
    private static final Pattern pattern = Pattern.compile(".*rememberMe=deleteMe.*");

    private final String base;

    private final HttpURLConnection connection;

    public ShiroHttpConnection(String base) throws IOException {
        this.base = base;
        this.connection = (HttpURLConnection) new URL(base).openConnection();
    }

    /**
     * 检测key是否错误
     * @param rememberMe Cookie
     * @return key错误，返回true；key正确，返回false
     */
    public boolean checkFalseKey(String rememberMe) {
        try {
            this.sendRememberMe(rememberMe, true);
            Map<String, List<String>> fields = connection.getHeaderFields();
            List<String> setCookie = fields.get("Set-Cookie");

            return setCookie.stream().anyMatch(line -> pattern.matcher(line).matches());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return false;
    }

    public void sendRememberMe(String rememberMe, boolean keyCheck) throws Exception {
        // 探测key，请求方式只能为GET，使用POST请求方式获得响应头的时候会出现BUG
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36");
        connection.setRequestProperty("Cookie", "rememberMe=" + rememberMe);
        connection.setDoOutput(true);
        int responseCode = connection.getResponseCode();
        System.out.println("[-] Response Code: " + responseCode);
        if (!keyCheck) {
            connection.disconnect();
        }
    }
}
