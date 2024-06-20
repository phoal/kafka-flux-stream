package phoal.kafkaflux.core

import phoal.kafkaflux.budget.write.entity.Budget
import phoal.kafkaflux.budget.write.entity.BudgetChange
import phoal.kafkaflux.message.Message

enum class Context {
    Message,
    Budget,
}

enum class EventType(val context: Context, val clazz: Class<*>) {
    // Command
    CreateBudget(Context.Budget, Budget::class.java),
    UpdateBudget(Context.Budget, BudgetChange::class.java),

    Test(Context.Message, String::class.java),
    MessageSent(Context.Message, Message::class.java),

    // Event
    BudgetUpdated(Context.Budget, BudgetChange::class.java),
}