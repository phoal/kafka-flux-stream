package phoal.kafkaflux.message

import java.time.Instant
import java.util.*

data class MessageDTO(
    val uuid: UUID,
    val timeStamp: Instant,
    val sender: String,
    val eventType: String,
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