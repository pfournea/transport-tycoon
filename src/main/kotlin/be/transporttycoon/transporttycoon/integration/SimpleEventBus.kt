package be.transporttycoon.transporttycoon.integration

import be.transporttycoon.transporttycoon.EventBus
import be.transporttycoon.transporttycoon.EventListener
import be.transporttycoon.transporttycoon.domain.event.Event

object SimpleEventBus : EventBus {
    private val eventListeners = mutableSetOf<EventListener>()

    override fun publish(event: Event) {
        eventListeners.forEach { listener -> listener.consumeEvent(event) }
    }

    override fun addEventListeners(eventListeners: Set<EventListener>) {
        SimpleEventBus.eventListeners.addAll(eventListeners)
    }
}