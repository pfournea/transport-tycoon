package be.transporttycoon.transporttycoon

class Truck : AbstractTransport(Location.FACTORY) {


    override fun loadTransport(cargo: Cargo) {
//        deliveryPosition = when (cargo.destination) {
//            Location.A -> 1
//            Location.B -> 5
//        }
        destination =  when (cargo.destination) {
            Location.A -> Location.PORT
            else -> cargo.destination
        }
        this.cargo = cargo
        this.location = cargo.orgin
        this.cargo?.linkedToVehicleOrBoat = true
        step = 1
    }

}