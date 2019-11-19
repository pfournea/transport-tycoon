package be.transporttycoon.transporttycoon

import org.junit.jupiter.api.Test

internal class TransportServiceTest {

    @Test
    fun testA() {
        val transportList = listOf(Cargo(Location.A, 1))
        val processTime = TransportService.getTimeToHandleTransport(transportList)
        assert(processTime == 5)
    }

    @Test
    fun testAB() {
        val transportList = listOf(Cargo(Location.A,1), Cargo(Location.B, 2))
        val processTime = TransportService.getTimeToHandleTransport(transportList)
        assert(processTime == 5)
    }

    @Test
    fun testBB() {
        val transportList = listOf(Cargo(Location.B,1), Cargo(Location.B,2))
        val processTime = TransportService.getTimeToHandleTransport(transportList)
        assert(processTime == 5)
    }

    @Test
    fun testAABABBAB() {
        val transportList = createTransportList("AABABBAB")
        val processTime = TransportService.getTimeToHandleTransport(transportList)
        assert(processTime == 29)
    }

    @Test
    fun testABBBABAAABBB() {
        val transportList = createTransportList("ABBBABAAABBB")
        val processTime = TransportService.getTimeToHandleTransport(transportList)
        assert(processTime == 41)
    }

    private fun createTransportList(s: String): List<Cargo> {
        var id : Long = 0
        return s.toCharArray().map { Cargo(Location.valueOf(it.toString()), id++) }
    }
}