package phoal.kafkaflux.core

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Service
import phoal.kafkaflux.message.Message

@Service
interface Handler {
    val eventType: EventType
    fun handle(message: Message)
}