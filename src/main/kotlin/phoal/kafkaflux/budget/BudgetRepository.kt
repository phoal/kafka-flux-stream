package phoal.kafkaflux.budget

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import phoal.kafkaflux.budget.entity.Budget

@Repository
interface BudgetRepository : ReactiveCrudRepository<Budget, String>