package be.transporttycoon.transporttycoon.domain

import be.transporttycoon.transporttycoon.domain.event.Event

interface EventRepository {
    fun getAllEvents() : List<Event>

    fun storeEvent(event : Event)
}