package phoal.kafkaflux.message

import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
import org.springframework.kafka.support.serializer.JsonDeserializer
import reactor.kafka.receiver.ReceiverOptions

@Configuration
class KafkaReceiverOptions {

    @Bean
    fun receiverOptions(properties: KafkaProperties): ReceiverOptions<String, Message> {
        val consumerProperties = properties.buildConsumerProperties()
        val deserializer = ErrorHandlingDeserializer<String>(StringDeserializer())
        return ReceiverOptions.create<String, Message>(consumerProperties)
            .withKeyDeserializer(deserializer)
            .withValueDeserializer(JsonDeserializer(Message::class.java))
    }
}