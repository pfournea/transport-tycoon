package be.transporttycoon.transporttycoon

import be.transporttycoon.transporttycoon.domain.event.Event

interface EventListener {

    fun consumeEvent(event : Event)
}