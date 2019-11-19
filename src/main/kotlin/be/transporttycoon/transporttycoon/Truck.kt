package be.transporttycoon.transporttycoon

class Truck : AbstractTransport(Location.FACTORY) {


    override fun loadTransport(cargo: Cargo) {
        destination =  when (cargo.destination) {
            Location.A -> Location.PORT
            else -> cargo.destination
        }
        this.cargo = cargo
        this.location = cargo.origin
        this.cargo?.linkedToVehicleOrBoat = true
        step = 1
    }

}