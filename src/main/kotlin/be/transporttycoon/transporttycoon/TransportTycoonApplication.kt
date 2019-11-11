package be.transporttycoon.transporttycoon


class TransportTycoonApplication

fun main(args: Array<String>) {
    val input = readLine()!!
    //create transports
    val transportList = input.toCharArray().map { Transport(Destination.valueOf(it.toString())) }
    println("Total time to process the transportList ${TransportService.getTimeToHandleTransport(transportList)}")
}


