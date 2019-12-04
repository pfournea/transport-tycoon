package be.transporttycoon.transporttycoon.domain

import be.transporttycoon.transporttycoon.integration.SimpleEventBus
import be.transporttycoon.transporttycoon.domain.event.*
import be.transporttycoon.transporttycoon.event.*

class Transport private constructor(val transportId: Int, val transportType: TransportType, val originLocation: Location) {
    var currentLocation: Location? = originLocation
    var destinationLocation: Location? = null
    var currentPosition: Int
    var cargoList = mutableListOf<Cargo>()
    var maxCargo: Int

    init {
        currentPosition = when (transportType) {
            TransportType.TRUCK -> 0
            TransportType.BOAT -> 1
        }
        maxCargo = when (transportType) {
            TransportType.TRUCK -> 1
            TransportType.BOAT -> 1
        }
    }

    companion object {
        fun createTruck(transportId: Int): Transport {
            return Transport(transportId, TransportType.TRUCK, Location.FACTORY)
        }

        fun createBoat(transportId: Int): Transport {
            return Transport(transportId, TransportType.BOAT, Location.PORT)
        }
    }

    private fun loadCargo(cargo: Cargo) {
        destinationLocation = when (transportType) {
            TransportType.TRUCK -> if (cargo.destinationLocation == Location.A) Location.PORT else cargo.destinationLocation
            TransportType.BOAT -> cargo.destinationLocation
        }
        cargo.cargoLoadedOnTransport()
        cargoList.add(cargo)
    }

    fun unloadCargo(world: World) {
        val cargoToUnload = cargoList.removeAt(0)
        cargoToUnload.cargoDeliveredOn(destinationLocation!!)
        val storage = world.findStorage(destinationLocation!!)
        storage?.addCargoToStorage(cargoToUnload)
    }

    fun move(world: World) {
        //back @ Factory or Port
        if (destinationLocation != null) {
            if (currentPosition == destinationLocation!!.position) {
                currentLocation = destinationLocation
                SimpleEventBus.publish(createTransportArrivedEvent(world))
                //determine new destinatinLocation
                //  if (currentLocation != originLocation) {
                //      destinationLocation = originLocation
                //  }
            }
        }
        if (currentLocation == originLocation) {
            val storage = world.findStorage(currentLocation!!)
            if (storage != null) {
                val potentialCargoToLoad = storage.getOldestCargoFromStorage()
                potentialCargoToLoad?.let { loadCargo(potentialCargoToLoad) }
                if (potentialCargoToLoad == null) {
                    destinationLocation = null
                }
            }
        } else {
            //at location B or Port
            if (currentLocation != null && currentLocation == destinationLocation) {
                unloadCargo(world)
                destinationLocation = originLocation
            }
        }
        if (destinationLocation != null && destinationLocation != originLocation) {
            if (currentLocation == originLocation) {
                //make DEPART_EVENT
                SimpleEventBus.publish(createTransportDepartedEvent(world))
                currentLocation = null
            }
            currentPosition += 1
        } else if (destinationLocation != null && destinationLocation == originLocation) {
            if (currentLocation != null) {
                //make DEPART_EVENT
                SimpleEventBus.publish(createTransportDepartedEvent(world))
                currentLocation = null
            }
            currentPosition -= 1
        }
        //determine if we arrived at our destination


    }

    private fun createTransportArrivedEvent(world: World): Event {
        val transportInformation = TransportInformation(
                time = world.getCurrentTime(),
                location = this.currentLocation?.name ?: "",
                destination = this.destinationLocation?.name ?: "",
                transport_id = transportId,
                type = this.transportType.name
        )
        val cargo = if (cargoList.isEmpty()) null else CargoInformation(cargo_id = cargoList[0].id,
                destination = cargoList[0].destinationLocation.name,
                origin = cargoList[0].originLocation.name)
        return TransportArrivedEvent(transportInformation = transportInformation, cargo = cargo)

    }

    private fun createTransportDepartedEvent(world: World): Event {
        val transportInformation = TransportInformation(
                time = world.getCurrentTime(),
                location = this.currentLocation?.name ?: "",
                destination = this.destinationLocation?.name ?: "",
                transport_id = transportId,
                type = this.transportType.name
        )
        val cargo = if (cargoList.isEmpty()) null else CargoInformation(cargo_id = cargoList[0].id,
                destination = cargoList[0].destinationLocation.name,
                origin = cargoList[0].originLocation.name)
        return TransportDepartedEvent(transportInformation = transportInformation, cargo = cargo)
    }
}

enum class TransportType {
    TRUCK,
    BOAT
}