package phoal.kafkaflux.budget.handler

import org.springframework.stereotype.Service
import phoal.kafkaflux.core.EventType
import phoal.kafkaflux.budget.entity.BudgetChange
import phoal.kafkaflux.budget.BudgetChangeRepository
import phoal.kafkaflux.core.Mapper
import phoal.kafkaflux.core.WriteHandler
import java.util.logging.Logger

@Service
class BudgetChangeHandler(
    override val repository: BudgetChangeRepository,
    override val mapper: Mapper,
    override val logger: Logger
): WriteHandler<BudgetChange> {
    override val eventType = EventType.UpdateBudget
}