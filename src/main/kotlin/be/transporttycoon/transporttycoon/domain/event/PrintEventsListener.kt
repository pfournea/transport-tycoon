package be.transporttycoon.transporttycoon.domain.event

import be.transporttycoon.transporttycoon.EventListener
import be.transporttycoon.transporttycoon.util.EventToJsonUtil

class PrintEventsListener : EventListener {
    override fun consumeEvent(event: Event) {
        when(event) {
            is TransportDepartedEvent -> printTransportDepartedEvent(event)
            is TransportArrivedEvent -> printTransportDepartedEvent(event)
        }
    }

    private fun printTransportDepartedEvent(event: TransportArrivedEvent) {
        println(EventToJsonUtil.toString(event))

    }

    private fun printTransportDepartedEvent(event: TransportDepartedEvent) {
        println(EventToJsonUtil.toString(event))
    }

}


//{"event": "DEPART", "time": 0, "transport_id": 0, "kind": "TRUCK", "location": "FACTORY", "destination": "PORT", "cargo": [{"cargo_id": 0, "destination": "A", "origin": "FACTORY"}]}
//{"event": "DEPART", "time": 0, "transport_id": 1, "kind": "TRUCK", "location": "FACTORY", "destination": "B", "cargo": [{"cargo_id": 1, "destination": "B", "origin": "FACTORY"}]}