package phoal.kafkaflux.message

import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate
import reactor.kafka.sender.SenderOptions

@Configuration
class ProducerConfig {
    @Bean
    fun reactiveKafkaProducerTemplate(
        properties: KafkaProperties
    ): ReactiveKafkaProducerTemplate<String, Message> {
        val props = properties.buildProducerProperties()
        return ReactiveKafkaProducerTemplate<String, Message>(SenderOptions.create(props))
    }
}