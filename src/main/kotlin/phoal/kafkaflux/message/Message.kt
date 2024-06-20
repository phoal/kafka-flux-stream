package phoal.kafkaflux.message

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import phoal.kafkaflux.core.EventType
import java.time.Instant
import java.util.UUID

@Table(schema = "message")
data class Message(
    @Id
    val uuid: UUID,
    val timeStamp: Instant,
    val sender: Sender,
    val eventType: EventType,
    val content: String,
    val status: Int,
)



