package be.transporttycoon.transporttycoon

class Cargo(val destination: Location, val id : Long, val origin : Location = Location.FACTORY) {
    var position = 0
    var linkedToVehicleOrBoat = false

    fun arrivedAtDestination() : Boolean = position >= destination.position

    fun atFactory() : Boolean = position == origin.position

    fun move() {
        position += 1
    }
}