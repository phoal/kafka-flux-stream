package phoal.kafkaflux.core

import org.springframework.stereotype.Service
import phoal.kafkaflux.message.Message

@Service
interface Dispatcher {
    fun dispatch(message: Message)
}