package phoal.kafkaflux.core

import org.springframework.stereotype.Service
import phoal.kafkaflux.message.Message

@Service
interface Dispatcher<T: Any, S: Handler<T>> {
    val handlers: Set<S>
    fun dispatch(message: Message) {
        val handler = handlers.find { it -> it.eventType == message.eventType }
        handler?.handle(message)
    }

}