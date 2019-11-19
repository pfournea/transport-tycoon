package be.transporttycoon.transporttycoon


class TransportTycoonApplication

fun main(args: Array<String>) {
    val input = readLine()!!
    //create transports
    var cargoIdCounter : Long = 0
    val transportList = input.toCharArray().map { Cargo(Location.valueOf(it.toString()), cargoIdCounter++) }
    println("Total time to process the transportList ${TransportService.getTimeToHandleTransport(transportList)}")
}


