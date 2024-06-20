package phoal.kafkaflux.budget.common

import java.math.BigDecimal
import java.util.*

interface BudgetBase {
    val id: Int?
    val userId: Int
    val name: String
    val category: Category
    val amount: BigDecimal
    val year: Int
    val uuid: UUID
}