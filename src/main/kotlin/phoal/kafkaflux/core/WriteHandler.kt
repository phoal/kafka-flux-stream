package phoal.kafkaflux.core

import org.springframework.data.repository.Repository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Service
import phoal.kafkaflux.log.LogUtility
import phoal.kafkaflux.message.Message
import java.util.logging.Level
import java.util.logging.Logger

@Service
interface WriteHandler<T : Any> : Handler {
    val mapper: Mapper
    val repository: ReactiveCrudRepository<T, Long>
    val logger: Logger

    override fun handle(message: Message) {
        logger.log(Level.WARNING, """WriteInsert: ${message.content}""")
        logger.log(Level.WARNING, """WriteInsert: ${mapper.deserialize<T>(message)}""")

        repository.save(mapper.deserialize<T>(message))
            .doOnError { e ->
                logger.log(Level.WARNING, """WriteInsert: ${e.message}""")
            }
            .subscribe()
    }
}
