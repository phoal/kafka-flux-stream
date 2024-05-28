package phoal.kafkaflux.core

import org.springframework.stereotype.Service
import phoal.kafkaflux.message.Message
import reactor.core.publisher.Mono

@Service
interface Handler {
    val eventType: EventType
    fun handle(message: Message)
}