package no.sasoria.springboot.Models

import spock.lang.Specification

class PlayerListTest extends Specification {

    def playerList = new PlayerList()

    def player

    def "Test for loading player with service" () {
        given:
            player = new Player(8,"John","bf4","Norway", 32)

        when:
            playerList.addPlayer(player)

        then:
            playerList.getPlayers().size() == 1

    }

    def "Test for getting player with service" () {
        given:
            player = new Player(8,"John","bf4","Norway", 32)

        when:
            playerList.addPlayer(player)

        then:
            playerList.getPlayer("John").getName() == "John"
    }

    def "Test for having player with service" () {
        given:
            player = new Player(8,"John","bf4","Norway", 32)

        when:
            playerList.addPlayer(player)

        then:
            playerList.hasPlayer("John")
    }

    def "Test for clearing players with service" () {
        given:
            player = new Player(8,"John","bf4","Norway", 32)

        when:
            playerList.addPlayer(player)
            playerList.clear()

        then:
            playerList.getPlayers().size() == 0
    }

}
