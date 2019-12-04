package be.transporttycoon.transporttycoon.domain.event

data class TransportDepartedEvent(val cargo : CargoInformation?, val transportInformation: TransportInformation) : Event {

}

data class TransportArrivedEvent(val cargo: CargoInformation?, val transportInformation: TransportInformation) : Event

data class CargoInformation (val cargo_id : Int, val destination : String, val origin : String)


//{"event": "DEPART", "time": 0, "transport_id": 0, "kind": "TRUCK", "location": "FACTORY", "destination": "PORT", "cargo": [{"cargo_id": 0, "destination": "A", "origin": "FACTORY"}]}
//{"event": "DEPART", "time": 0, "transport_id": 1, "kind": "TRUCK", "location": "FACTORY", "destination": "B", "cargo": [{"cargo_id": 1, "destination": "B", "origin": "FACTORY"}]}
