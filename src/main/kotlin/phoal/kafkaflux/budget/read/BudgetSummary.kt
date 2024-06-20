package phoal.kafkaflux.budget.read

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import phoal.kafkaflux.budget.common.BudgetBase
import phoal.kafkaflux.budget.common.Category
import phoal.kafkaflux.budget.write.entity.Budget
import java.math.BigDecimal
import java.util.*

@Table(schema = "budget_read")
data class BudgetSummary(
    @Id
    override val id: Int?,
    override val userId: Int,
    override val name: String,
    override val category: Category,
    override val amount: BigDecimal,
    override val year: Int,
    override val uuid: UUID,
    val total: BigDecimal,
    val summary: String,
): BudgetBase

fun Budget.toSummary(budget: Budget, total: BigDecimal, summary: String): BudgetSummary {
    return BudgetSummary(
        id = this.id,
        userId = this.userId,
        name = this.name,
        category = this.category,
        amount = this.amount,
        year = this.year,
        uuid = this.uuid,
        total = total,
        summary = summary,
    )
}