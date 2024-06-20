package phoal.kafkaflux.budget.read

import org.springframework.stereotype.Service
import phoal.kafkaflux.message.MessageRepository
import reactor.core.publisher.Mono
import java.util.logging.Logger

@Service
class BudgetSummaryService(
    val repository: BudgetSummaryRepository,
    val logger: Logger
) {
    fun customInsert(budgetSummary: BudgetSummary): Mono<BudgetSummary> {
        val (id, userId, name, category ,amount, year, uuid, total, summary) = budgetSummary
        return repository.customInsert(
            id!!,
            userId,
            name,
            category,
            amount,
            year,
            uuid,
            total,
            summary
        )
    }
}