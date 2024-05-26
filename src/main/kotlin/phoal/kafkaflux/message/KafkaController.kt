package phoal.kafkaflux.message

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.ConnectableFlux
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/kafka")
class KafkaController(val connectableFlux: ConnectableFlux<Message>, val messageService: MessageService) {

//    fun receiverOptionsForTopic(): ReceiverOptions<String, Message> {
//
//        val consumerProperties = receiverOptions.consumerProperties()
//        val topicExists = doesTopicExists(Config.MESSAGE_TOPIC, consumerProperties)
//        require(topicExists) { "Topic does not exist: ${Config.MESSAGE_TOPIC}" }
//
//        val topicNames = listOf(Config.MESSAGE_TOPIC)
//        val kafkaOptions = receiverOptions.subscription(topicNames)
//
//        kafkaOptions.addAssignListener { partitions: Collection<ReceiverPartition> ->
//            partitions.forEach(Consumer { obj: ReceiverPartition -> obj.seekToBeginning() })
//        }
//
//
//        // More options can be added here if needed
//        return kafkaOptions
//    }
//
//    companion object {
//        fun doesTopicExists(
//            topicName: String, properties: Map<String, Any>?
//        ): Boolean {
//            AdminClient.create(properties).use { admin ->
//                return admin.listTopics().names().get().stream().anyMatch { topic: String ->
//                    topic.equals(
//                        topicName, ignoreCase = true
//                    )
//                }
//            }
//        }
//    }

//    final fun createConnectableFlux(): ConnectableFlux<Message> {
//        return ReactiveKafkaConsumerTemplate(KafkaReceiver.create(receiverOptionsForTopic)).receiveAutoAck()
//            .doOnError { throwable ->
//                Logger.getAnonymousLogger()
//                    .log(Level.SEVERE, "Error receiving event on service topic:", throwable.message)
//            }.map { record -> record.value() }
//            .doOnNext { message -> Logger.getAnonymousLogger().log(Level.INFO, """successfully consumed: $message""") }
//            .publish()
//    }
//
//    val connectableFlux = createConnectableFlux()

    @GetMapping(value = ["/stream"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    @ResponseBody
    fun stream(): Flux<Message> {
        return connectableFlux.autoConnect()
    }

    @PostMapping(value = ["/send"], consumes = ["application/json"], produces = ["application/json"])
    @ResponseBody
    fun sendMessage(@RequestBody message: Message) {
//        try {
            //Sending the message to kafka topic queue
        messageService.send(message)
//        } catch (e: InterruptedException) {
//            throw RuntimeException(e)
//        } catch (e: ExecutionException) {
//            throw RuntimeException(e)
//        }
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun createMessage(@RequestBody message: Message): Mono<Message> {
        return messageService.customInsert(message)
    }
}
