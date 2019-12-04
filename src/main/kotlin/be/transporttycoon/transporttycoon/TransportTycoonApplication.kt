package be.transporttycoon.transporttycoon

import be.transporttycoon.transporttycoon.domain.Cargo
import be.transporttycoon.transporttycoon.domain.Location


class TransportTycoonApplication

fun main(args: Array<String>) {
    val input = readLine()!!
    var cargoIdCounter = 0

    val transportList = input.toCharArray().map { Cargo(originLocation = Location.FACTORY, destinationLocation = Location.valueOf(it.toString()),id = cargoIdCounter++) }
    //create the locations

    println("Total time to process the transportList ${TransportService.getTimeToHandleTransport(transportList)}")
}


