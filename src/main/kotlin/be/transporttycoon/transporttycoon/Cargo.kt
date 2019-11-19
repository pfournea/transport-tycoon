package be.transporttycoon.transporttycoon

class Cargo(val destination: Location, val id : Long, val orgin : Location = Location.FACTORY) {
    var position = 0
    var linkedToVehicleOrBoat = false

    fun arrivedAtDestination() : Boolean = position >= 5

    fun atFactory() : Boolean = position == 0

    fun move() {
        position += 1
    }
}