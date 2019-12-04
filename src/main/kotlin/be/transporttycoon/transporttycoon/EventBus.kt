package be.transporttycoon.transporttycoon

import be.transporttycoon.transporttycoon.domain.event.Event

interface EventBus {

    fun publish(event : Event)

    fun addEventListeners(eventListeners : Set<EventListener>)
}