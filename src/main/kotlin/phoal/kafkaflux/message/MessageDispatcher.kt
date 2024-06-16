package phoal.kafkaflux.message

import org.springframework.stereotype.Service
import phoal.kafkaflux.core.Dispatcher
import phoal.kafkaflux.core.Handler
import reactor.core.publisher.ConnectableFlux
import reactor.core.publisher.Mono
import java.util.logging.Level
import java.util.logging.Logger

@Service
class MessageDispatcher(
    final val connectableFlux: ConnectableFlux<Message>,
    override val handlers: Set<Handler>,
    messageService: MessageService,
    logger: Logger,
): Dispatcher {
    val handlerFlux = connectableFlux
        .autoConnect()
        .doOnNext { message -> logger.log(Level.INFO, """MessageDispatcher: $message""") }
        .flatMap { message ->  messageService.customInsert(message)
            .onErrorResume { e ->
                logger.log(Level.WARNING, """MessageInsert: ${e.message}""")
                Mono.just(message) }}
        .doOnNext { message -> dispatch(message) }
        .subscribe()
}