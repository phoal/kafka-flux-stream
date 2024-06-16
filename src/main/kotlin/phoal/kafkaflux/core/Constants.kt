package phoal.kafkaflux.core

import phoal.kafkaflux.message.utility.MessageRequest

object Constants {
    val budgetAndUpdate: List<MessageRequest> = listOf(
        MessageRequest(EventType.CreateBudget, 1),
        MessageRequest(EventType.UpdateBudget, 2)
    )
}