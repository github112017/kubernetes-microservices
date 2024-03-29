package no.sasoria.springboot.Service

import spock.lang.Specification


class LoadServiceTest extends Specification {

    def loadService = new LoadService()

    def "Test for loading player with service" () {

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
            loadService.loadPlayer("John")
            loadService.clearPlayers()

        then:
            loadService.getPlayers().size == 0
    }

}
