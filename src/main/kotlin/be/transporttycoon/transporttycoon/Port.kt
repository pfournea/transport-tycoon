package be.transporttycoon.transporttycoon

class Port {
    val transportList = mutableListOf<Cargo>()

    fun getOldestTransport(): Cargo? {
        return if (transportList.isNotEmpty()) {
            transportList.removeAt(0)
        } else {
            null
        }
    }
}