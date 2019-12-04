package be.transporttycoon.transporttycoon.integration

import be.transporttycoon.transporttycoon.EventListener
import be.transporttycoon.transporttycoon.domain.EventRepository
import be.transporttycoon.transporttycoon.domain.event.Event

class InMemoryEventRepository : EventRepository, EventListener {
    val eventStorage : MutableList<Event> = ArrayList()
    override fun getAllEvents(): List<Event> {
        return eventStorage.toList()
    }

    override fun storeEvent(event: Event) {
        eventStorage.add(event)
    }

    override fun consumeEvent(event: Event) {
        storeEvent(event)
    }
}