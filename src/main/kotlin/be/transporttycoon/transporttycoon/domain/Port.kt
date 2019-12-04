package be.transporttycoon.transporttycoon.domain

class Port(cargoStorage: MutableList<Cargo>) : Store(cargoStorage, Location.PORT) {
}