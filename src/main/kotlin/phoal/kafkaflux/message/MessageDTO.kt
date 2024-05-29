package phoal.kafkaflux.message

import phoal.kafkaflux.core.EventType
import java.time.Instant
import java.util.*

data class MessageDTO(
    val uuid: UUID,
    val timeStamp: Instant,
    val sender: String,
    val eventType: EventType,
//    @JdbcTypeCode(SqlTypes.JSON)
    val content: String,
    val status: Int,
)

fun toMessage(message: MessageDTO): Message {
    return Message(
        uuid = message.uuid,
        timeStamp = message.timeStamp,
        sender = message.sender,
        eventType = message.eventType,
        content = message.content,
        status = message.status,
    )
}