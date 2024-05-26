package phoal.kafkaflux.message

import org.springframework.stereotype.Service
import reactor.core.publisher.ConnectableFlux
import reactor.core.publisher.Mono
import java.util.logging.Level
import java.util.logging.Logger

@Service
class MessageHandler(
    final val connectableFlux: ConnectableFlux<Message>,
    messageRepository: MessageRepository,
    messageService: MessageService,
) {
    val handlerFlux = connectableFlux
        .autoConnect()
        .doOnNext { message -> Logger.getAnonymousLogger().log(Level.INFO, """MessageHandler: $message""") }
        .flatMap { message ->  messageService.customInsert(message).onErrorResume { _ -> Mono.just(message) }}
        .subscribe()
}