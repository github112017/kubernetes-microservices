package no.sasoria.springboot.Service;

import no.sasoria.springboot.Models.Player;
import no.sasoria.springboot.Models.PlayerList;
import no.sasoria.springboot.Utils.JsonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public class LoadService {

    @Autowired
    PlayerList playerlist;

    /**
     * Loads a player into a {@code List} if from a http get request if the request was successful.
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
     * Gets a player with name {@code name}.
     * @param name
     * @return
     */
    public Player getPlayer(String name) {
        Player player = playerlist.getPlayer(name);
        return player;
    }

    /**
     * Checks if list contains a player with name {@code name}.
     * @param name
     * @return
     */
    public boolean hasPlayer(String name) {
        return playerlist.hasPlayer(name);
    }

    /**
     * Gets player the player list.
     * @return List of players
     */
    public List<Player> getPlayers(){
        return playerlist.getPlayers();
    }

    /**
     * Clears data in the player list.
     * @return
     * @throws Exception
     */
    public boolean clearPlayers() throws Exception {
        return playerlist.clear();
    }

    /**
     * Checks http status codes of a http request.
     * @param response
     * @param entity
     * @param name
     * @return true if the status code is successful, i.e 200. false otherwise.
     * @throws IOException
     */
    private Boolean checkStatuscode(HttpResponse response, HttpEntity entity, String name) throws IOException {
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            JSONObject jsonPlayer = JsonUtils.extractPlayerFromResponse(entity);

            playerlist.addPlayer(new Player(jsonPlayer));
            return true;
        }
        else if (statusCode == 404) {
            /* TODO check correct apache.client.error
                notFound : 404
            */
            String error = JsonUtils.extractErrorFromResponse(entity);
            // FIXME : this handles as a 500 internal server error
            throw new HttpResponseException(404, "Player not found, error: " + error);
        }
        else if (statusCode == 500) {
            /* TODO check correct apache.client.error
                nameInvalid  : 500
                nameLong     : 500
                nameShort    : 500
                notPublic    : ?
            */
            String error = JsonUtils.extractErrorFromResponse(entity);
            throw new ClientProtocolException(error);
        }
        else {
            return false;
        }
    }

    /**
     * Creates an URL for a http REST request.
     * @param name
     * @return url for playerinfo, platform: pc
     */
    private String createRequestURL(String name) {
        String baseUrl = "http://api.bf4stats.com/api/playerInfo?";
        String platform = "plat=pc";
        String playerName = "&name=" + name;
        String output = "&output=json";

        return baseUrl + platform + playerName + output;
    }
}
