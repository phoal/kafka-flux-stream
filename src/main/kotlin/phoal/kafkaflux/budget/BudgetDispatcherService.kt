package phoal.kafkaflux.budget

import org.springframework.stereotype.Service
import phoal.kafkaflux.core.DispatcherService
import phoal.kafkaflux.budget.dispatcher.BudgetHandler
import phoal.kafkaflux.core.Context
import phoal.kafkaflux.core.Handler
import phoal.kafkaflux.message.Message
import reactor.core.publisher.ConnectableFlux
import reactor.core.publisher.Mono
import java.util.logging.Level
import java.util.logging.Logger

@Service
class BudgetDispatcherService(
    override val handlers: Set<BudgetHandler>,
    final val connectableFlux: ConnectableFlux<Message>,
): DispatcherService<BudgetHandler> {
    val handlerFlux = connectableFlux
        .autoConnect()
        .doOnNext { message -> Logger.getAnonymousLogger().log(Level.INFO, """FinanceHandler: $message""") }
        .filter { message -> message.eventType.context == Context.Budget }
        .map { message -> message}
        .subscribe()
}