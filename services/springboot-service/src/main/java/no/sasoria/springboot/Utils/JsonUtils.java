package no.sasoria.springboot.Utils;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JsonUtils {

    /**
     *
     * @param entity
     * @return
     * @throws IOException
     */
    public static JSONObject extractPlayerFromResponse(HttpEntity entity) throws IOException {
        String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        JSONObject jsonObject = new JSONObject(json);
        return jsonObject.getJSONObject("player");
    }

    /**
     *
     * @param entity
     * @return
     * @throws IOException
     */
    public static String extractErrorFromResponse(HttpEntity entity) throws IOException {
        String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        JSONObject jsonObject = new JSONObject(json);
        return jsonObject.getString("error");
    }
}
