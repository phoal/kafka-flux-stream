package phoal.kafkaflux.core

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.stereotype.Component
import phoal.kafkaflux.message.Message
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule

@Component
class Mapper {
    private val objectMapper = jacksonObjectMapper().apply {
        registerKotlinModule()
        registerModule(JavaTimeModule())
    }

    fun <T> deserialize(message: Message): T {
        return objectMapper.readValue(message.content, message.eventType.clazz as Class<T>)
    }

    fun serialize(value: Any): String {
        return objectMapper.writeValueAsString(value)
    }
}