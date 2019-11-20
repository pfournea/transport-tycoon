package be.transporttycoon.transporttycoon

class Boat : AbstractTransport(Location.PORT) {

    init {
        this.position = this.startLocation.position
    }

    override fun loadTransport(cargo: Cargo) {
        this.cargo = cargo
        this.destination = cargo.destination
        this.location = cargo.origin
        this.cargo?.linkedToVehicleOrBoat = true
    }
}