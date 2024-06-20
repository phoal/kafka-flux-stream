package phoal.kafkaflux.budget.write.handler

import org.springframework.stereotype.Service
import phoal.kafkaflux.budget.write.entity.BudgetChangeRepository
import phoal.kafkaflux.budget.write.entity.BudgetChange
import phoal.kafkaflux.core.EventType
import phoal.kafkaflux.core.Mapper
import phoal.kafkaflux.core.WriteHandler
import phoal.kafkaflux.message.MessageService
import java.util.logging.Logger

@Service
class BudgetChangeHandler(
    override val repository: BudgetChangeRepository,
    override val mapper: Mapper,
    override val logger: Logger,
    override val messageService: MessageService
): WriteHandler<BudgetChange> {
    override val eventType = EventType.UpdateBudget
}