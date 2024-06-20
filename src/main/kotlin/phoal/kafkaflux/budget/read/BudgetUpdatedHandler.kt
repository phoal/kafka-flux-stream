package phoal.kafkaflux.budget.read

import phoal.kafkaflux.budget.write.entity.BudgetChange
import phoal.kafkaflux.core.EventType
import phoal.kafkaflux.core.Handler
import phoal.kafkaflux.message.Message
import phoal.kafkaflux.message.notification
import reactor.core.Disposable
import java.math.BigDecimal

class BudgetUpdatedHandler(
    override val eventType: EventType = EventType.BudgetUpdated,
    val summaryRepository: BudgetSummaryRepository,
    val service: BudgetSummaryService,
): Handler {
    override fun handle(message: Message) {
        val change = mapper.deserialize<BudgetChange>(message)
        summaryRepository.findById(change.id!!)
            .flatMap { it ->
                    val map = mapper.toMap(it.summary) as MutableMap<String, BigDecimal>
                    val key = it.category.toString()
                    val total = map.getOrDefault(it.category.toString(), BigDecimal(0))
                    map.put(key, total.plus(change.amount))
                    service.customInsert(it)
            }
            .flatMap { entity -> messageService.notifyProcessed(message.notification()) }
            .subscribe()
    }
}
