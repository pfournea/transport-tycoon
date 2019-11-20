package be.transporttycoon.transporttycoon


abstract class AbstractTransport(val startLocation : Location) {
    var position: Int = 0
    var cargo: Cargo? = null
    var destination : Location? = null
    var location : Location? = null

    fun atDeparture(): Boolean = position == startLocation.position

    fun atDestination(): Boolean = position == destination?.position ?: false

    abstract fun loadTransport(cargo: Cargo)

    fun move() {
        cargo?.let {
            position += 1
            it.move()
        }
        if (returningToDeparture()) {
            position += -1
        }
    }

    fun unload() : Cargo? {
        val cargoToUnload = this.cargo
        cargo?.linkedToVehicleOrBoat = false
        cargo = null
        return cargoToUnload
    }

    fun returningToDeparture(): Boolean = cargo == null && atDeparture().not()
}