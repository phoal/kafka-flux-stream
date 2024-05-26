package phoal.kafkaflux.finance.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.Instant
import java.util.*

@Table(schema = "finance")
data class BudgetChange(
    @Id
    val id: Long? = null,
    val budget: Long,
    val category: Category,
    val modifier: Modifier,
    val amount: BigDecimal,
    val timestamp: Instant,
    val uuid: UUID,
)
