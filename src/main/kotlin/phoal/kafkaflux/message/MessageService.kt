package phoal.kafkaflux.message

import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate
import org.springframework.stereotype.Service
import phoal.kafkaflux.message.Config.MESSAGE_TOPIC
import reactor.core.publisher.Mono
import reactor.kafka.sender.SenderResult
import java.util.logging.Level
import java.util.logging.Logger


@Service
class MessageService(val reactiveKafkaProducerTemplate: ReactiveKafkaProducerTemplate<String, Message>, val repository: MessageRepository, val logger: Logger) {

    fun send(message: Message) {
        logger.log(Level.INFO, """Attempt send to topic $MESSAGE_TOPIC $message""")
        reactiveKafkaProducerTemplate.send(MESSAGE_TOPIC, message)
            .doOnSuccess { senderResult: SenderResult<Void?> ->
                logger.log(Level.INFO, """RESULT SENT ${senderResult.recordMetadata().timestamp()} ${senderResult.correlationMetadata()}, $message, OFFSET ${senderResult.recordMetadata().offset()}""")
            }
            .subscribe()
    }

    fun save(message: Message): Mono<Message> {
        return repository.save(message.copy())
    }

    fun customInsert(message: Message): Mono<Message> {
        val (uuid, timeStamp, sender, eventType, content, status) = message
//        return repository.customInsert(message)
        return repository.customInsert(  uuid,   timeStamp,   sender,   eventType,   content,   status)
    }
}