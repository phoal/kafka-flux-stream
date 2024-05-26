package phoal.kafkaflux.core

import org.springframework.stereotype.Service
import phoal.kafkaflux.message.Message

@Service
interface Handler {
    fun handle(message: Message)
}