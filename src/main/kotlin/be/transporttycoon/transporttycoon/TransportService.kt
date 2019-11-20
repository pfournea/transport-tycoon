package be.transporttycoon.transporttycoon

object TransportService {

    fun getTimeToHandleTransport(cargoList: List<Cargo>): Int {
        val mutableCargoList = cargoList.toMutableList()
        var totalTime = -1
        val truck1 = Truck()
        val truck2 = Truck()
        val boat = Boat()
        val trucks = listOf(truck1, truck2)
        val port = Port()
        do {
            totalTime += 1

            trucks.forEach { truck ->
                if (truck.atDeparture()) {
                    val transportAvailableForTruck = findTransportForTruck(mutableCargoList)
                    transportAvailableForTruck?.let { truck.loadTransport(it) }
                }
                if (truck.atDestination()) {
                    val cargoAtDestination = truck.unload()
                    if (truck.destination == Location.PORT) {
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
        } while (cargoList.any { it.arrivedAtDestination().not() })
        return totalTime
    }

    private fun findTransportForBoat(port: Port): Cargo? {
        return port.getOldestTransport()
    }

    private fun findTransportForTruck(cargoList: MutableList<Cargo>) = if (cargoList.isEmpty()) null else cargoList.removeAt(0)
}