package be.transporttycoon.transporttycoon

class Cargo(val destination: Location, val id : Long, val originLocation : Location = Location.FACTORY) {
    var linkedToVehicleOrBoat = false
    var dropOfLocation : Location? = originLocation
    fun arrivedAtDestination() : Boolean = dropOfLocation == destination
}