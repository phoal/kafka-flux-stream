package phoal.kafkaflux.message.utility

import phoal.kafkaflux.core.EventType

data class MessageRequest(
    val eventType: EventType,
    val quantity: Int,
)
