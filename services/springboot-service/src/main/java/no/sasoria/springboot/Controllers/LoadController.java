package no.sasoria.springboot.Controllers;

import no.sasoria.springboot.Models.Player;
import no.sasoria.springboot.Service.LoadService;
import org.apache.http.client.ClientProtocolException;

import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoadController {

    @Autowired
    private LoadService loadService;

    private RestTemplate restTemplate;

    public LoadController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    protected LoadService getLoadService() {
        return loadService;
    }

    /**
     * Loads player with {@code name} using the REST API from bf4stats.com and adds it to a {@code List}.
     * @param model
     * @param name of player
     * @return
     * @throws IOException
     */
    @PutMapping({"/api/player"})
    @ResponseStatus(HttpStatus.OK)
    public String loadPlayer(Model model, @RequestParam(value="name") String name) throws IOException {
        if(!hasPlayer(name)) {
            throw new HttpResponseException(404, "Player not found, error: ");
            //loadService.loadPlayer(name);
           // return "Player loaded";
        }
        throw new IllegalArgumentException("Player already loaded");
    }

    protected boolean hasPlayer(String name) {
        return loadService.hasPlayer(name);
    }

    /**
     * GETs player from a {@code List}.
     * @param model
     * @param name of player
     * @return json representation of a player
     */
    @GetMapping({"/api/player"})
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getPlayer(Model model, @RequestParam(value="name") String name) {
        if (hasPlayer(name)) {
            Map<String, Object> response = new HashMap<>();
            Player player = loadService.getPlayer(name);

            response.put("name", player.getName());
            response.put("country", player.getCountry());
            response.put("game", player.getGame());
            response.put("rank", player.getRank());

            return response;
        }
        else {
            throw new IllegalArgumentException("Player does not exist");
        }
    }

    /**
     * Loads players from a json list with player names using the REST API from
     * bf4stats.com and adds it to a {@code List}.
     * @param model
     * @return a json list of players.
     * @throws ClientProtocolException
     * @throws IOException
     */
    @PutMapping({"/api/players"})
    public String loadData(Model model) {
        // TODO : implement this.
        if (true) {
            return "Players loaded";
        }
        else {
            throw new IllegalArgumentException("Failed to load players");
        }
    }

    /**
     * Gets players from a {@code List} and returns them as a json list.
     * @param model
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    @GetMapping({"/api/players"})
    public Map<Integer, Object> get_players(Model model) throws ClientProtocolException, IOException {
        List<Player> players = loadService.getPlayers();
        Map<Integer, Object> response = new HashMap<>();

        if(players.isEmpty()) {
            throw new RuntimeException("Failed to get players");
        }
        else {
            for (Player player : players) {
                response.put(player.getId(), player.toJSON());
            }

            return response;
        }
    }

    /**
     * Clears all players in the list of players.
     * @return
     * @throws Exception
     */
    @DeleteMapping({"/api/players"})
    public String clearData() throws Exception {
        // TODO : test this with httpie.
        if (loadService.clearPlayers())
            return "cleared players";

        throw new RuntimeException("Failed to clear players");
    }

}

