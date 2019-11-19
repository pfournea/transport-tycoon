package be.transporttycoon.transporttycoon


abstract class AbstractTransport(val startLocation : Location) {
    var position: Int = 0
    var cargo: Cargo? = null
    var step = 1
    var destination : Location? = null
    var location : Location? = null

    fun atDeparture(): Boolean = position == startLocation.position

    fun atDestination(): Boolean = position == destination?.position ?: false

    abstract fun loadTransport(cargo: Cargo)

    fun move() {
        cargo?.let {
            position += step
            it.move()
        }
        if (returningToDeparture()) {
            position += step
        }
    }

    fun unload() : Cargo? {
        val cargoToUnload = this.cargo
        cargo?.linkedToVehicleOrBoat = false
        cargo = null
        step = -1
        return cargoToUnload
    }

    fun returningToDeparture(): Boolean = cargo == null && atDeparture().not()
}