package phoal.kafkaflux.finance.dispatcher

import org.springframework.stereotype.Service
import phoal.kafkaflux.core.Converter
import phoal.kafkaflux.finance.entity.BudgetChange
import phoal.kafkaflux.finance.BudgetChangeRepository
import phoal.kafkaflux.message.Message

@Service
class BudgetChangeDispatcher(
    val budgetChangeRepository: BudgetChangeRepository,
): FinanceDispatcher {
    val converter: Converter<BudgetChange> = Converter(BudgetChange::class.java)

    override fun dispatch(message: Message) {
        budgetChangeRepository.save(converter.convert(message)).subscribe()
    }
}