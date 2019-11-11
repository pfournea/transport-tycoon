package be.transporttycoon.transporttycoon

class Port {
    val transportList = mutableListOf<Transport>()

    fun getOldestTransport(): Transport? {
        return if (transportList.isNotEmpty()) {
            transportList.removeAt(0)
        } else {
            null
        }
    }
}