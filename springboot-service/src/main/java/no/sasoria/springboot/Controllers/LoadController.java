package no.sasoria.springboot.Controllers;

import no.sasoria.springboot.Service.LoadService;
import org.apache.http.client.ClientProtocolException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoadController {

    @Autowired
    private LoadService loadService;

    @GetMapping({"/api/load_data"})
    public Map<String, Object>  loadData(Model model, @RequestParam(value="name", required=false, defaultValue="") String name) throws
            ClientProtocolException, IOException {

        if (loadService.loadPlayer(name)) {
            return loadService.getPlayer();
        }
        else {
            throw new RuntimeException("Failed to load data");
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

        return "data not cleared";
    }

}

