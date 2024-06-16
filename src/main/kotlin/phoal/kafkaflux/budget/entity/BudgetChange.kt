package phoal.kafkaflux.budget.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.Instant
import java.util.*

@Table(schema = "budget")
data class BudgetChange(
    @Id
    val id: Long? = null,
    val budget: Long,
    val category: Category,
    val modifier: Modifier,
    val amount: BigDecimal,
    val timeStamp: Instant,
    val uuid: UUID,
)
