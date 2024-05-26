package phoal.kafkaflux.core

import com.fasterxml.jackson.module.kotlin.*
import phoal.kafkaflux.message.Message

class Converter<T>(private val clazz: Class<T>) {
    private val objectMapper = jacksonObjectMapper().apply {
        registerKotlinModule()
    }

    fun convert(message: Message): T {
        return objectMapper.readValue(message.content, clazz)
    }
}