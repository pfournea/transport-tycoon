package be.transporttycoon.transporttycoon

object TransportService {

    fun getTimeToHandleTransport(transportList: List<Transport>): Int {
        var totalTime = 0
        val truck1 = Truck()
        val truck2 = Truck()
        val boat = Boat()
        val trucks = listOf(truck1, truck2)
        val port = Port()
        while (transportList.any { it.arrivedAtDestination().not() }) {
            trucks.forEach { truck ->
                if (truck.atFactory()) {
                    val transportAvailableForTruck = findTransportForTruck(transportList)
                    transportAvailableForTruck?.let { truck.loadTransport(it) }
                }
                if (truck.atDestination()) {
                    if (truck.transport!!.destination == Destination.A) {
                        port.transportList.add(truck.transport!!)
                    }
                    truck.unload()
                }

                truck.move()
            }

            if (boat.atPort()) {
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

    private fun findTransportForBoat(port: Port): Transport? {
        return port.getOldestTransport()
    }

    private fun findTransportForTruck(transportList: List<Transport>) =
            transportList.firstOrNull { transport -> transport.atFactory() && transport.linkedToVehicleOrBoat.not() }
}