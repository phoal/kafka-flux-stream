package phoal.kafkaflux.finance.dispatcher

import org.springframework.stereotype.Service
import phoal.kafkaflux.core.Converter
import phoal.kafkaflux.core.Dispatcher
import phoal.kafkaflux.finance.entity.Budget
import phoal.kafkaflux.finance.BudgetRepository
import phoal.kafkaflux.message.Message

@Service
class BudgetDispatcher(
    val budgetRepository: BudgetRepository,
): Dispatcher {
    val converter: Converter<Budget> = Converter<Budget>(Budget::class.java)

    override fun dispatch(message: Message) {
        budgetRepository.save(converter.convert(message)).subscribe()
    }
}