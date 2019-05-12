package no.sasoria.springboot.Service

import no.sasoria.springboot.Models.Player
import spock.lang.Specification


class PlayerListTest extends Specification {

    def loadService = new LoadService()
    def player


    def "Test for loading player with service" () {
        given:
            player = new Player(0, "John", "bf4", "Norway", 34)

        when:
            loadService.loadPlayer("John")

        then:
            loadService.getPlayers().size() == 1

    }

    def "Test for getting player with service" () {
        when:
            loadService.loadPlayer("John")

        then:
            loadService.getPlayer("John").getName() == "John"
    }

    def "Test for having player with service" () {
        when:
            loadService.loadPlayer("John")

        then:
            loadService.hasPlayer("John")
    }

    def "Test for clearing players with service" () {
        when:
            loadService.clearPlayers()

        then:
            loadService.hasPlayer("John")
    }

}
