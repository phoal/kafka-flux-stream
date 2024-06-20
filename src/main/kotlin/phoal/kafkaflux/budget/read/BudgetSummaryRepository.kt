package phoal.kafkaflux.budget.read

import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import phoal.kafkaflux.budget.common.Category
import reactor.core.publisher.Mono
import java.math.BigDecimal
import java.util.*

@Repository
interface BudgetSummaryRepository: ReactiveCrudRepository<BudgetSummary, Int> {
    @Query("""INSERT INTO budget_read.budget_summary (id, userId, name, category, amount, year, uuid, total, summary)  
           VALUES ( :id, :userId, :name, :category, :amount, :year, :uuid, :total, :summary) RETURNING *""")
    fun customInsert(
        id: Int,
        userId: Int,
        name: String,
        category: Category,
        amount: BigDecimal,
        year: Int,
        uuid: UUID,
        total: BigDecimal,
        summary: String,
    ): Mono<BudgetSummary>
}