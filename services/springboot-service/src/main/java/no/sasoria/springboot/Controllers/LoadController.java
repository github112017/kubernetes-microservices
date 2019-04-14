package no.sasoria.springboot.Controllers;

import no.sasoria.springboot.Models.Player;
import no.sasoria.springboot.Service.LoadService;
import org.apache.http.client.ClientProtocolException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoadController {

    @Autowired
    private LoadService loadService;

    @GetMapping({"/api/load_data"})
        public String loadData(Model model, @RequestParam(value="name", required=false, defaultValue="") String name) throws
        ClientProtocolException, IOException {
            if (loadService.loadPlayer(name)) {
                return "Data loaded";
            }
            else {
                throw new RuntimeException("Failed to load data");
            }
    }

    @GetMapping({"/api/get_player"})
    public Map<String, Object> getPlayer(Model model, @RequestParam(value="name", required=true) String name) throws
            ClientProtocolException, IOException {
        if (loadService.loadPlayer(name)) {
            return loadService.getPlayer(name);
        }
        else {
            throw new RuntimeException("Failed to get data");
        }
    }

    @GetMapping({"/api/get_players"})
    public Map<Integer, Object> get_players(Model model, @RequestParam(value="name", required=false, defaultValue="") String name) throws
            ClientProtocolException, IOException {
        if (loadService.loadPlayer(name)) {
            List<Player> players = loadService.getPlayers();

            Map<Integer, Object> response = new HashMap<>();
            for (Player player : players) {
                response.put(player.getId(), player.toJSON());
            }

            return response;
        }
        else {
            throw new RuntimeException("Failed to get data");
        }
    }

    @GetMapping({"/api/persist_data"})
    public String persistData(Model model, @RequestParam(value="name", required=false, defaultValue="") String name) {
        model.addAttribute("name", name);
        // TODO : load data from the json object into a MongoDB.
        return "data persisted";
    }

    @GetMapping({"/api/clear_data"})
    public String clearData(Model model) throws Exception {
        if (loadService.clearData())
            return "cleared data";

        return "Failed to clear data";
    }

}

