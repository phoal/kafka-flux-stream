package phoal.kafkaflux.budget.dispatcher

import org.springframework.stereotype.Service
import phoal.kafkaflux.core.Converter
import phoal.kafkaflux.core.EventType
import phoal.kafkaflux.budget.entity.BudgetChange
import phoal.kafkaflux.budget.BudgetChangeRepository
import phoal.kafkaflux.budget.BudgetRepository
import phoal.kafkaflux.budget.entity.Budget
import phoal.kafkaflux.message.Message

@Service
class BudgetChangeHandler(
    override val repository: BudgetChangeRepository,
): BudgetBaseHandler<BudgetChange> {
    override val eventType = EventType.CreateBudget
    override val converter = Converter(BudgetChange::class.java)
}