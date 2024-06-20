package phoal.kafkaflux.message.utility

import org.springframework.stereotype.Component
import phoal.kafkaflux.budget.write.entity.Budget
import phoal.kafkaflux.budget.write.entity.BudgetChange
import phoal.kafkaflux.budget.common.Category
import phoal.kafkaflux.budget.common.Modifier
import phoal.kafkaflux.core.EventType
import phoal.kafkaflux.core.Mapper
import phoal.kafkaflux.message.Message
import phoal.kafkaflux.message.Sender
import java.math.BigDecimal
import java.time.Instant
import java.util.*

@Component
class MessageGenerator(
    val mapper: Mapper
) {
    fun generate(eventType: EventType): Message {
        return when (eventType)  {
            EventType.CreateBudget -> generateMessage(eventType, mapper.serialize(generateBudget()))
            EventType.UpdateBudget -> generateMessage(eventType, mapper.serialize(generateBudgetChange()))
            else -> generateMessage(eventType, "{}")
        }
    }
}

fun generateBudget(): Budget {
    return Budget(
        userId = 1L,
        name = "Test",
        category = Category.Grocery,
        year = 2024,
        uuid = UUID.randomUUID()
    )
}

fun generateBudgetChange(): BudgetChange {
    return BudgetChange(
        budget = 1L,
        category = Category.Grocery,
        modifier = Modifier.Plus,
        amount = BigDecimal(12.00),
        timeStamp = Instant.now(),
        uuid = UUID.randomUUID(),
    )
}

fun generateMessage(eventType: EventType, content: String): Message {
    return Message(
        uuid = UUID.randomUUID(),
        eventType = eventType,
        sender = Sender.User,
        status = 100,
        timeStamp = Instant.now(),
        content = content,
    )
}
