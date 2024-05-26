package phoal.kafkaflux.finance

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import phoal.kafkaflux.finance.entity.Budget

@Repository
interface BudgetRepository : ReactiveCrudRepository<Budget, String>