package no.sasoria.springboot.Service;

import no.sasoria.springboot.Models.Player;
import no.sasoria.springboot.Models.PlayerList;
import no.sasoria.springboot.Utils.JsonUtils;
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

    /**
     * 
     * @param name
     * @return
     * @throws IOException
     */
    public boolean loadPlayer(String name) throws IOException {
        String url = createRequestURL(name);
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();

        return checkStatuscode(response, entity, name);
    }

    /**
     * 
     * @param name
     * @return
     */
    public Player getPlayer(String name) {
        Player player = playerlist.getPlayer(name);
        return player;
    }

    /**
     * 
     * @param name
     * @return
     */
    public boolean hasPlayer(String name) {
        return playerlist.hasPlayer(name);
    }

    /**
     * 
     * @return
     */
    public List<Player> getPlayers(){
        return playerlist.getPlayers();
    }

    /**
     * 
     * @return
     * @throws Exception
     */
    public boolean clearData() throws Exception {
        return playerlist.clear();
    }

    /**
     * 
     * @param response
     * @param entity
     * @param name
     * @return
     * @throws IOException
     */
    private Boolean checkStatuscode(HttpResponse response, HttpEntity entity, String name) throws IOException {
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            // JSONObject jsonPlayer = extractPlayerFromResponse(entity);
            JSONObject jsonPlayer = JsonUtils.extractPlayerFromResponse(entity);

            playerlist.addPlayer(new Player(jsonPlayer));
            return true;
        }
        else if (statusCode == 404) {
            /*
                TODO : handle,
                    notFound : 404
            */
            String error = JsonUtils.extractErrorFromResponse(entity);
            return false;
        }
        else if (statusCode == 500) {
            /*
                TODO : handle,
                    nameInvalid  : 500
                    nameLong     : 500
                    nameShort    : 500
                    notPublic    : ?
            */
            String error = JsonUtils.extractErrorFromResponse(entity);
            return false;
        }
        else {
            return false;
        }
    }

    /**
     * 
     * @param name
     * @return
     */
    private String createRequestURL(String name) {
        String baseUrl = "http://api.bf4stats.com/api/playerInfo?";
        String platform = "plat=pc";
        String playerName = "&name=" + name;
        String output = "&output=json";

        return baseUrl + platform + playerName + output;
    }
}
