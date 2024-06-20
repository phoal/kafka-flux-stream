package phoal.kafkaflux.budget.write.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import phoal.kafkaflux.budget.common.Category
import phoal.kafkaflux.budget.common.Modifier
import java.math.BigDecimal
import java.time.Instant
import java.util.*

@Table(schema = "budget")
data class BudgetChange(
    @Id
    val id: Int? = null,
    val budget: Int,
    val category: Category,
    val modifier: Modifier,
    val amount: BigDecimal,
    val timeStamp: Instant,
    val uuid: UUID,
)
