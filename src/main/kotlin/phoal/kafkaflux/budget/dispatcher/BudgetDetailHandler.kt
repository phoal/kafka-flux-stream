package phoal.kafkaflux.budget.dispatcher

import org.springframework.stereotype.Service
import phoal.kafkaflux.core.Converter
import phoal.kafkaflux.core.EventType
import phoal.kafkaflux.core.Handler
import phoal.kafkaflux.budget.entity.Budget
import phoal.kafkaflux.budget.BudgetRepository
import phoal.kafkaflux.message.Message

@Service
class BudgetDetailHandler(
    val budgetRepository: BudgetRepository,
): Handler {
    override val eventType = EventType.CreateBudget
    val converter: Converter<Budget> = Converter(Budget::class.java)

    override fun handle(message: Message) {
        budgetRepository.save(converter.convert(message)).subscribe()
    }
}