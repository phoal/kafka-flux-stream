package phoal.kafkaflux.core

import org.springframework.stereotype.Service
import phoal.kafkaflux.message.Message
import reactor.core.Disposable

@Service
interface Handler {
    val eventType: EventType
    val mapper: Mapper
    fun handle(message: Message): Disposable
}