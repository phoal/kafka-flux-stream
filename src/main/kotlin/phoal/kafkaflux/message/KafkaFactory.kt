package phoal.kafkaflux.message

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate
import reactor.core.publisher.ConnectableFlux
import reactor.kafka.receiver.KafkaReceiver
import reactor.kafka.receiver.ReceiverOptions
import java.util.logging.Level
import java.util.logging.Logger

@Configuration
class KafkaFactory(val receiverOptionsForTopic: ReceiverOptions<String, Message>) {

    @Bean
    fun connectableFlux(): ConnectableFlux<Message> {
        return ReactiveKafkaConsumerTemplate(KafkaReceiver.create(receiverOptionsForTopic))
            .receiveAutoAck()
            .doOnError { throwable ->
                Logger.getAnonymousLogger().log(Level.SEVERE, "Error receiving event on service topic:", throwable.message) }
            .map { record -> record.value() }
            .doOnNext { message -> Logger.getAnonymousLogger().log(Level.INFO, """successfully consumed: $message""") }
            .publish()
    }
}