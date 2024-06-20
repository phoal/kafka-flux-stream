package phoal.kafkaflux.budget.write.entity

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import phoal.kafkaflux.budget.write.entity.BudgetChange

@Repository
interface BudgetChangeRepository: ReactiveCrudRepository<BudgetChange, Int> {
}