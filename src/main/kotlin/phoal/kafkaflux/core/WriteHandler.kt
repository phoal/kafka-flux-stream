package phoal.kafkaflux.core

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Service
import phoal.kafkaflux.message.Message
import phoal.kafkaflux.message.MessageService
import phoal.kafkaflux.message.notification
import reactor.core.Disposable
import reactor.core.publisher.Mono
import java.util.logging.Level
import java.util.logging.Logger

@Service
interface WriteHandler<T : Any> : Handler {
    val repository: ReactiveCrudRepository<T, Int>
    val logger: Logger
    val messageService: MessageService

    override fun handle(message: Message): Disposable {
        return save(message)
            .flatMap { entity -> messageService.notifyProcessed(message.notification()) }
            .subscribe()
    }

    fun save(message: Message): Mono<T> {
        logger.log(Level.WARNING, """WriteInsert: ${message.content}""")
        logger.log(Level.WARNING, """WriteInsert: ${mapper.deserialize<T>(message)}""")

        return repository.save(mapper.deserialize<T>(message))
            .doOnError { e ->
                logger.log(Level.WARNING, """WriteError: ${e.message}""")
            }
    }
}
