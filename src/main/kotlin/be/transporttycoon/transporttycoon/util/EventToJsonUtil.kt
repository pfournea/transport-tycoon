package be.transporttycoon.transporttycoon.util

import be.transporttycoon.transporttycoon.domain.event.Event
import be.transporttycoon.transporttycoon.domain.event.TransportArrivedEvent
import be.transporttycoon.transporttycoon.domain.event.TransportDepartedEvent

object EventToJsonUtil {
    fun toString(event: Event): String {
        return when (event) {
            is TransportArrivedEvent -> this.toString(event)
            is TransportDepartedEvent -> this.toString(event)
            else -> ""
        }
    }

    fun toString(event: TransportArrivedEvent): String {
        return if (event.cargo != null) {
            """{"event": "ARRIVE", "time": ${event.transportInformation.time}, "transport_id": ${event.transportInformation.transport_id}, "kind": "${event.transportInformation.type}", "location": "${event.transportInformation.location}", "destination": "${event.transportInformation.destination}", "cargo": [{"cargo_id": ${event.cargo.cargo_id}, "destination": "${event.cargo.destination}", "origin": "${event.cargo.origin}"}]}"""
        } else {
            """{"event": "ARRIVE", "time": ${event.transportInformation.time}, "transport_id": ${event.transportInformation.transport_id}, "kind": "${event.transportInformation.type}", "location": "${event.transportInformation.location}", "destination": "${event.transportInformation.destination}"}"""
        }
    }

    fun toString(event: TransportDepartedEvent): String {
        return if (event.cargo != null) {
            """{"event": "DEPART", "time": ${event.transportInformation.time}, "transport_id": ${event.transportInformation.transport_id}, "kind": "${event.transportInformation.type}", "location": "${event.transportInformation.location}", "destination": "${event.transportInformation.destination}", "cargo": [{"cargo_id": ${event.cargo.cargo_id}, "destination": "${event.cargo.destination}", "origin": "${event.cargo.origin}"}]}"""
        } else {
            """{"event": "DEPART", "time": ${event.transportInformation.time}, "transport_id": ${event.transportInformation.transport_id}, "kind": "${event.transportInformation.type}", "location": "${event.transportInformation.location}", "destination": "${event.transportInformation.destination}"}"""
        }
    }
}