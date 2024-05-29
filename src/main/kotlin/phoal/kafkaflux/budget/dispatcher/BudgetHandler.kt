package phoal.kafkaflux.budget.dispatcher

import org.springframework.stereotype.Service
import phoal.kafkaflux.core.EventType
import phoal.kafkaflux.budget.BudgetRepository
import phoal.kafkaflux.budget.entity.Budget
import phoal.kafkaflux.core.Converter

@Service
class BudgetHandler(
    override val repository: BudgetRepository,
): BudgetBaseHandler<Budget> {
    override val eventType = EventType.CreateBudget
    override val converter = Converter(Budget::class.java)
}