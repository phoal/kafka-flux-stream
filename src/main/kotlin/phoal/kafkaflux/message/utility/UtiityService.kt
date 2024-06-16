package phoal.kafkaflux.message.utility

import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate
import org.springframework.stereotype.Service
import phoal.kafkaflux.message.Config
import phoal.kafkaflux.message.Message
import reactor.kafka.sender.SenderResult
import java.util.logging.Level
import java.util.logging.Logger

@Service
class UtilityService(
    val logger: Logger,
    val generator: MessageGenerator,
    val template: ReactiveKafkaProducerTemplate<String, Message>,
) {

    fun send(requests: List<MessageRequest>) {
        logger.log(Level.INFO, """Message Requests $requests""")
        requests.flatMap { generate(it) }
            .forEach {
                template.send(Config.MESSAGE_TOPIC, it)
                    .doOnSuccess { senderResult: SenderResult<Void?> ->
                        logger.log(
                            Level.INFO,
                            """RESULT SENT ${
                                senderResult.recordMetadata().timestamp()
                            } ${senderResult.correlationMetadata()}, $it, OFFSET ${
                                senderResult.recordMetadata().offset()
                            }"""
                        )
                    }
                    .subscribe()
            }
    }

    fun generate(request: MessageRequest): List<Message> {
        val (eventType, quantity) = request
        return (1..quantity).map { generator.generate(eventType) }
    }
}