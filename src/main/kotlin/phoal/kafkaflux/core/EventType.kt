package phoal.kafkaflux.core

enum class Context {
    Message,
    Budget,
}

enum class EventType(val context: Context) {
    UpdateBudget(Context.Budget),
    CreateBudget(Context.Budget),
    Test(Context.Message),
}