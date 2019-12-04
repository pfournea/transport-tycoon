package be.transporttycoon.transporttycoon.domain

data class Cargo(val id : Int, val originLocation : Location = Location.FACTORY, val destinationLocation : Location) {
    private var currentLocation : Location?
    private var handledByTransport : Boolean = false
    init {
        currentLocation = originLocation
    }

    fun cargoLoadedOnTransport() {
        currentLocation = null
        handledByTransport = true
    }

    fun cargoDeliveredOn(location : Location) {
        currentLocation = location
        handledByTransport = false
    }

    fun cargoOnDestination() : Boolean {
        return currentLocation == destinationLocation
    }
}