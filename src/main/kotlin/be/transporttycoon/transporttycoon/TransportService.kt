package be.transporttycoon.transporttycoon

object TransportService {

    fun getTimeToHandleTransport(cargoList: List<Cargo>): Int {
        var totalTime = 0
        val truck1 = Truck()
        val truck2 = Truck()
        val boat = Boat()
        val trucks = listOf(truck1, truck2)
        val port = Port()
        while (cargoList.any { it.arrivedAtDestination().not() }) {
            trucks.forEach { truck ->
                if (truck.atDeparture()) {
                    val transportAvailableForTruck = findTransportForTruck(cargoList)
                    transportAvailableForTruck?.let { truck.loadTransport(it) }
                }
                if (truck.atDestination()) {
                    val cargoAtDestination = truck.unload()
                    if (truck.destination == Location.PORT && cargoAtDestination != null) {
                        port.transportList.add(cargoAtDestination)
                    }
                }
                truck.move()
            }

            if (boat.atDeparture()) {
                val transport = findTransportForBoat(port)
                transport?.let { boat.loadTransport(it) }
            }

            if (boat.atDestination()) {
                boat.unload()
            }

            boat.move()

            totalTime += 1
        }
        return totalTime
    }

    private fun findTransportForBoat(port: Port): Cargo? {
        return port.getOldestTransport()
    }

    private fun findTransportForTruck(cargoList: List<Cargo>) =
            cargoList.firstOrNull { transport -> transport.atFactory() && transport.linkedToVehicleOrBoat.not() }
}