package be.transporttycoon.transporttycoon.domain

import be.transporttycoon.transporttycoon.Timer

class World(val factory: Factory, val port: Port, val timer: Timer) {
    fun findStorage(currentLocation: Location): Store? {
        if (factory.location == currentLocation) {
            return factory
        }

        if (port.location == currentLocation) {
            return port
        }

        return null
    }

    fun getCurrentTime() = timer.time

}