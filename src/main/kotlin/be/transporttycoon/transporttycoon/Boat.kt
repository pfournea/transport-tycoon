package be.transporttycoon.transporttycoon

class Boat : AbstractTransport(Location.PORT) {

    override fun loadTransport(cargo: Cargo) {
        this.cargo = cargo
        this.step = 1
        this.destination = cargo.destination
        this.location = cargo.origin
        this.cargo?.linkedToVehicleOrBoat = true
    }
}