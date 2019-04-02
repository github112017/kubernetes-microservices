package no.sasoria.springboot.Models;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class Player {
    int id, rank;
    String name, game, country;

    public Player() {}

    public Player(int id, String name, String game, String country, int rank) {
        this.id = id;
        this.name = name;
        this.game = game;
        this.country = country;
        this.rank = rank;
    }

    public Player(JSONObject jsonPlayer) {
        this.id = Integer.parseInt(jsonPlayer.get("id").toString());
        this.name = jsonPlayer.get("name").toString();
        this.game = jsonPlayer.get("game").toString();
        this.country = jsonPlayer.get("countryName").toString();
        this.rank = Integer.parseInt(jsonPlayer.getJSONObject("rank").get("nr").toString());
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
