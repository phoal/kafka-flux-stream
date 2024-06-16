package phoal.kafkaflux.budget.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.util.*

@Table(schema = "budget")
data class Budget(
    @Id
    val id: Long? = null,
    val user: Long,
    val name: String,
    val category: Category,
    val amount: BigDecimal? = null,
    val year: Int,
    val uuid: UUID,
)
