package phoal.kafkaflux.core

import org.springframework.stereotype.Service
import phoal.kafkaflux.message.Message

@Service
interface Dispatcher {
    val handlers: Set<Handler>
    fun dispatch(message: Message) {
        val handler = handlers.find { it -> it.eventType == message.eventType }
        handler?.handle(message)
    }
}