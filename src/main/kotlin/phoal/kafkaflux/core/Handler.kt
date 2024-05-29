package phoal.kafkaflux.core

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Service
import phoal.kafkaflux.budget.entity.Budget
import phoal.kafkaflux.budget.entity.BudgetChange
import phoal.kafkaflux.message.Message
import reactor.core.publisher.Mono

@Service
interface Handler<T : Any> {
    val eventType: EventType
    val converter: Converter<T>
    val repository: ReactiveCrudRepository<T, Long>

    fun handle(message: Message) {
        repository.save(converter.convert(message))
    }
}