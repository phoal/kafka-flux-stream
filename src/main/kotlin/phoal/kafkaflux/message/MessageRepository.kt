package phoal.kafkaflux.message

import org.springframework.data.r2dbc.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import phoal.kafkaflux.core.EventType
import reactor.core.publisher.Mono
import java.time.Instant
import java.util.*

@Repository
interface MessageRepository : ReactiveCrudRepository<Message, String> {
    @Query("""INSERT INTO message.message (uuid, time_stamp, sender, event_type, content, status)  
           VALUES ( :uuid,  :timeStamp,  :sender,  :eventType,  :content,  :status) RETURNING *""")
    fun customInsert(
        uuid: UUID,
        timeStamp: Instant,
        sender: Sender,
        eventType: EventType,
        content: String,
        status: Int
    ): Mono<Message>
}
