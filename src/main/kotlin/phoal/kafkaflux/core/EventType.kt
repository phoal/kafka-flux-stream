package phoal.kafkaflux.core

import phoal.kafkaflux.budget.entity.Budget
import phoal.kafkaflux.budget.entity.BudgetChange
import phoal.kafkaflux.message.Message

enum class Context {
    Message,
    Budget,
}

enum class EventType(val context: Context, val clazz: Class<*>) {
    UpdateBudget(Context.Budget, BudgetChange::class.java),
    CreateBudget(Context.Budget, Budget::class.java),
    Test(Context.Message, String::class.java),
    MessageSent(Context.Message, Message::class.java),
}