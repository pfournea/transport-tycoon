package be.transporttycoon.transporttycoon

class Transport(val destination: Destination) {
    var position = 0
    var linkedToVehicleOrBoat = false

    fun arrivedAtDestination() : Boolean = position >= 5

    fun atFactory() : Boolean = position == 0

    fun move() {
        position += 1
    }
}