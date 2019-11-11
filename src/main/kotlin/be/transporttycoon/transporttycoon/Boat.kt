package be.transporttycoon.transporttycoon

class Boat {

    var position: Int = 0
    private val deliveryPosition: Int = 4
    var transport: Transport? = null
    var step = 1

    fun atPort(): Boolean = position == 0

    fun atDestination(): Boolean = position == deliveryPosition

    fun loadTransport(transport: Transport) {
        this.transport = transport
        this.step = 1
        this.transport?.linkedToVehicleOrBoat = true
    }

    fun move() {
        transport?.let {
            position += step
            it.move()
        }
        if (returningToPort()) {
            position += step
        }
    }

    private fun returningToPort(): Boolean = transport == null && atPort().not()

    fun unload() {
        transport = null
        step = -1
        transport?.linkedToVehicleOrBoat = false
    }
}