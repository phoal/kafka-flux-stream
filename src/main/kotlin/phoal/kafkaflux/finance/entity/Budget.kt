package phoal.kafkaflux.finance.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.util.*

@Table(schema = "finance")
data class Budget(
    @Id
    val id: Long? = null,
    val user: Long,
    val name: Category,
    val amount: BigDecimal?,
    val year: Int,
    val uuid: UUID,
)
