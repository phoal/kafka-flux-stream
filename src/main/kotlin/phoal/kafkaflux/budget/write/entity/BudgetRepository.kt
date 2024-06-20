package phoal.kafkaflux.budget.write.entity

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BudgetRepository : ReactiveCrudRepository<Budget, Int>