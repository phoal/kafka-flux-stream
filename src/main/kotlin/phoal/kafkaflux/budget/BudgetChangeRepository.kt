package phoal.kafkaflux.budget

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import phoal.kafkaflux.budget.entity.BudgetChange

@Repository
interface BudgetChangeRepository : ReactiveCrudRepository<BudgetChange, String> {
}