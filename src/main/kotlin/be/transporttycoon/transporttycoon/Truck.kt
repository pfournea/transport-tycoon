package be.transporttycoon.transporttycoon

class Truck {
    var position: Int = 0
    private var deliveryPosition: Int = Int.MIN_VALUE
    var transport: Transport? = null
    var step = 1

    fun atFactory(): Boolean = position == 0

    fun atDestination(): Boolean = position == deliveryPosition

    fun loadTransport(transport: Transport) {
        deliveryPosition = when (transport.destination) {
            Destination.A -> 1
            Destination.B -> 5
        }
        this.transport = transport
        this.transport?.linkedToVehicleOrBoat = true
        step = 1
    }

    fun move() {
        transport?.let {
            position += step
            it.move()
        }
        if (returningToFactory()) {
            position += step
        }
    }

    fun unload() {
        transport?.linkedToVehicleOrBoat = false
        transport = null
        step = -1
    }

    fun returningToFactory(): Boolean = transport == null && atFactory().not()

}