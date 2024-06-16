package phoal.kafkaflux.budget.handler

import org.springframework.stereotype.Service
import phoal.kafkaflux.core.EventType
import phoal.kafkaflux.budget.BudgetRepository
import phoal.kafkaflux.budget.entity.Budget
import phoal.kafkaflux.core.Mapper
import phoal.kafkaflux.core.WriteHandler
import java.util.logging.Logger

@Service
class BudgetHandler(
    override val repository: BudgetRepository,
    override val mapper: Mapper,
    override val logger: Logger
): WriteHandler<Budget> {
    override val eventType = EventType.CreateBudget
}