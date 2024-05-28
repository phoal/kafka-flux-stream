package phoal.kafkaflux.budget.dispatcher

import org.springframework.stereotype.Service
import phoal.kafkaflux.core.Converter
import phoal.kafkaflux.core.EventType
import phoal.kafkaflux.budget.entity.BudgetChange
import phoal.kafkaflux.budget.BudgetChangeRepository
import phoal.kafkaflux.message.Message

@Service
class BudgetChangeHandler(
    val budgetChangeRepository: BudgetChangeRepository,
): BudgetHandler {
    override val eventType = EventType.UpdateBudget
    val converter: Converter<BudgetChange> = Converter(BudgetChange::class.java)

    override fun handle(message: Message) {
        budgetChangeRepository.save(converter.convert(message)).subscribe()
    }
}