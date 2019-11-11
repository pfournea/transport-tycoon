package be.transporttycoon.transporttycoon

import org.junit.jupiter.api.Test

internal class TransportServiceTest {

    @Test
    fun testA() {
        val transportList = listOf(Transport(Destination.A))
        val processTime = TransportService.getTimeToHandleTransport(transportList)
        assert(processTime == 5)
    }

    @Test
    fun testAB() {
        val transportList = listOf(Transport(Destination.A), Transport(Destination.B))
        val processTime = TransportService.getTimeToHandleTransport(transportList)
        assert(processTime == 5)
    }

    @Test
    fun testBB() {
        val transportList = listOf(Transport(Destination.B), Transport(Destination.B))
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

    private fun createTransportList(s: String): List<Transport> {
        return s.toCharArray().map { Transport(Destination.valueOf(it.toString())) }
    }
}