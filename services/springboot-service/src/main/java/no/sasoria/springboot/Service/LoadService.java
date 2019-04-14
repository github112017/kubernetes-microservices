package no.sasoria.springboot.Service;

import no.sasoria.springboot.Models.Player;
import no.sasoria.springboot.Models.PlayerList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoadService {

    @Autowired
    PlayerList playerlist;

    public boolean loadPlayer(String name) throws IOException {
        String url = createRequestURL(name);
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();

        return checkStatuscode(response, entity, name);
    }

    public Player getPlayer(String name) {
        Player player = playerlist.getPlayer(name);
        return player;
    }

    public List<Player> getPlayers(){
        return playerlist.getPlayers();
    }

    public boolean clearData() throws Exception {
        return playerlist.clear();
    }

    private Boolean checkStatuscode(HttpResponse response, HttpEntity entity, String name) throws IOException {
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonPlayer = jsonObject.getJSONObject("player");

            playerlist.addPlayer(new Player(jsonPlayer));
            boolean hasJazy2 = playerlist.hasPlayer("Jazy2");

            System.out.println("hasJazy2 : " + hasJazy2);
            return true;
        }
        else {
            return false;
        }
    }

    private String createRequestURL(String name) {
        String baseUrl = "http://api.bf4stats.com/api/playerInfo?";
        String platform = "plat=pc";
        String playerName = "&name=" + name;
        String output = "&output=json";

        return baseUrl + platform + playerName + output;
    }
}
