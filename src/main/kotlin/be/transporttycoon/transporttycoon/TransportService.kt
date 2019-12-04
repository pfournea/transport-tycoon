package be.transporttycoon.transporttycoon

import be.transporttycoon.transporttycoon.domain.*
import be.transporttycoon.transporttycoon.domain.event.Event
import be.transporttycoon.transporttycoon.domain.event.PrintEventsListener
import be.transporttycoon.transporttycoon.integration.InMemoryEventRepository
import be.transporttycoon.transporttycoon.integration.SimpleEventBus
import be.transporttycoon.transporttycoon.util.EventToJsonUtil
import java.io.BufferedWriter
import java.io.File

object TransportService {

    fun getTimeToHandleTransport(cargoList: List<Cargo>): Int {
        val eventRepository = InMemoryEventRepository()
        SimpleEventBus.addEventListeners(setOf(PrintEventsListener(), eventRepository))
        //create Factory
        val factory = Factory(cargos = cargoList.toMutableList())
        val port = Port(cargoStorage = mutableListOf())
        //create transports
        val truck_1 = Transport.createTruck(0)
        val truck_2 = Transport.createTruck(1)
        val boat = Transport.createBoat(2)
        val timer = Timer
        val world = World(factory, port, timer)
        timer.resetTime()

        while (atLeastOneCargoNotYetArrivedOnDestination(cargoList)) {
            timer.increaseTime()
            truck_1.move(world)
            truck_2.move(world)
            boat.move(world)
        }
        dropAllEventsToFile(cargoList.map { it.destinationLocation.name }.joinToString(separator = ""), eventRepository.getAllEvents())
        return timer.time
    }

    private fun dropAllEventsToFile(fileName: String, allEvents: List<Event>) {
        val file = File("$fileName.log")
        val isFileCreated = file.createNewFile()
        if(!isFileCreated) {
            file.delete()
            file.createNewFile()
        }
        val bw = file.bufferedWriter()
        bw.use { bufferedWriter: BufferedWriter -> allEvents.map { event -> bufferedWriter.write(EventToJsonUtil.toString(event))
        bufferedWriter.newLine()} }
    }

    fun atLeastOneCargoNotYetArrivedOnDestination(cargoList: List<Cargo>): Boolean {
        return cargoList.find { it.cargoOnDestination().not() } != null
    }
}