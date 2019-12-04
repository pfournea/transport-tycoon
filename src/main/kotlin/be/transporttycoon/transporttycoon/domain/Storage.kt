package be.transporttycoon.transporttycoon.domain

abstract class Store(val cargoStorage: MutableList<Cargo> = mutableListOf(), val location : Location) {

    fun addCargoToStorage(cargo : Cargo) {
        cargoStorage.add(0,cargo)
    }

    fun getOldestCargoFromStorage() : Cargo? {
        return if (cargoStorage.isEmpty()) null else cargoStorage.removeAt(0)
    }
}