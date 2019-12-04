package be.transporttycoon.transporttycoon

import be.transporttycoon.transporttycoon.domain.Cargo
import be.transporttycoon.transporttycoon.domain.Location
import org.junit.jupiter.api.Test

internal class TransportServiceTest {

//    @Test
//    fun testA() {
//        val transportList = listOf(Cargo(destinationLocation = Location.A, id =  1))
//        val processTime = TransportService.getTimeToHandleTransport(transportList)
//        assert(processTime == 5)
//    }

//    @Test
//    fun testAB() {
//        val transportList = listOf(Cargo(destinationLocation = Location.A, id = 1), Cargo(destinationLocation = Location.B, id = 2))
//        val processTime = TransportService.getTimeToHandleTransport(transportList)
//        assert(processTime == 5)
//    }

//    @Test
//    fun testBB() {
//        val transportList = listOf(Cargo(destinationLocation = Location.B, id = 1), Cargo(destinationLocation = Location.B,id = 2))
//        val processTime = TransportService.getTimeToHandleTransport(transportList)
//        assert(processTime == 5)
//    }

    @Test
    fun testAABABBAB() {
        val transportList = createTransportList("AABABBAB")
        val processTime = TransportService.getTimeToHandleTransport(transportList)
        assert(processTime == 29)
    }

//    @Test
//    fun testABBBABAAABBB() {
//        val transportList = createTransportList("ABBBABAAABBB")
//        val processTime = TransportService.getTimeToHandleTransport(transportList)
//        assert(processTime == 41)
//    }

    private fun createTransportList(s: String): List<Cargo> {
        var id = 0
        return s.toCharArray().map { Cargo(destinationLocation = Location.valueOf(it.toString()), id = id++) }
    }
}