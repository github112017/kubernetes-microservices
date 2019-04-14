package no.sasoria.springboot.Models;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerList {
    private List<Player> players;

    public PlayerList() {
        players = new ArrayList<Player>();
    }

    public boolean addPlayer(Player player) {
        if (player == null)
            throw new IllegalArgumentException("Expected player, received null");

        players.add(player);

        return true;
    }

    public Player getPlayer(String name) {
        if (name == null)
            throw new IllegalArgumentException("Expected playerName, received null");

        for (Player player: players) {
            if (player.getName().equals(name))
                return player;
        }
        return null;
    }

    public boolean hasPlayer(String name) {
        if (name == null)
            throw new IllegalArgumentException("Expected playerName, received null");

        for (Player player: players) {
            if (player.getName().equals(name))
                return true;
        }
        return false;
    }

    public boolean clear() throws Exception {
        if (players.isEmpty())
            throw new Exception("List is already empty");
        else {
            players.clear();
            return true;
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

}
