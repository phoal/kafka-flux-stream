package phoal.kafkaflux.core

import org.springframework.stereotype.Service
import phoal.kafkaflux.message.Message
import reactor.core.publisher.Mono

@Service
interface DispatcherService<T: Handler> {
    val handlers: Set<T>
    fun dispatch(message: Message) {
        val handler = handlers.find { it -> it.eventType == message.eventType }
        handler?.handle(message)
    }

}