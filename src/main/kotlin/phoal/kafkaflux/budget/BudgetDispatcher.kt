package phoal.kafkaflux.budget

import org.springframework.stereotype.Service
import phoal.kafkaflux.core.Dispatcher
import phoal.kafkaflux.budget.dispatcher.BudgetBaseHandler
import phoal.kafkaflux.core.Context
import phoal.kafkaflux.message.Message
import reactor.core.publisher.ConnectableFlux
import java.util.logging.Level
import java.util.logging.Logger

@Service
class BudgetDispatcher(
    override val handlers: Set<BudgetBaseHandler<Any>>,
    final val connectableFlux: ConnectableFlux<Message>,
): Dispatcher<Any, BudgetBaseHandler<Any>> {
    val handlerFlux = connectableFlux
        .autoConnect()
        .doOnNext { message -> Logger.getAnonymousLogger().log(Level.INFO, """FinanceHandler: $message""") }
        .filter { message -> message.eventType.context == Context.Budget }
        .doOnNext { message -> dispatch(message) }
}