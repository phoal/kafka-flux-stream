package phoal.kafkaflux.budget.write.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import phoal.kafkaflux.budget.common.BudgetBase
import phoal.kafkaflux.budget.common.Category
import java.math.BigDecimal
import java.util.*

@Table(schema = "budget")
data class Budget(
    @Id
    override val id: Int? = null,
    override val userId: Int,
    override val name: String,
    override val category: Category,
    override val  amount: BigDecimal = BigDecimal(0),
    override val  year: Int,
    override val  uuid: UUID,
): BudgetBase
