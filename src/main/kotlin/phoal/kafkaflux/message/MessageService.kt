package phoal.kafkaflux.message

import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate
import org.springframework.stereotype.Service
import phoal.kafkaflux.core.EventType
import phoal.kafkaflux.message.Config.MESSAGE_TOPIC
import reactor.core.publisher.Mono
import reactor.kafka.sender.SenderResult
import java.util.*
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

    fun notifyProcessed(message: Message): Mono<SenderResult<Void>> {
        logger.log(Level.INFO, """Notify processed: $message""")
        return reactiveKafkaProducerTemplate.send(MESSAGE_TOPIC, message.notification())
    }

    fun save(message: Message): Mono<Message> {
        return repository.save(message.copy())
    }

    fun customInsert(message: Message): Mono<Message> {
        val (uuid, timeStamp, sender, eventType, content, status) = message
        return repository.customInsert(  uuid,   timeStamp,   sender,   eventType,   content,   status)
    }
}

fun Message.notification(): Message {
    val eventType = when (this.eventType) {
        EventType.UpdateBudget -> EventType.BudgetUpdated
        else -> this.eventType
    }
    return this.copy(
        uuid = UUID.randomUUID(),
        eventType = eventType,
        sender = Sender.System,
    )
}