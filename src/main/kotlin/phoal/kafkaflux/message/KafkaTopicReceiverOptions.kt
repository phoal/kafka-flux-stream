package phoal.kafkaflux.message

import org.apache.kafka.clients.admin.AdminClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.kafka.receiver.ReceiverOptions
import reactor.kafka.receiver.ReceiverPartition
import java.util.function.Consumer

@Configuration
class KafkaTopicReceiverOptions(val receiverOptions: ReceiverOptions<String, Message>) {

    @Bean
    fun receiverOptionsForTopic(): ReceiverOptions<String, Message> {

        val consumerProperties = receiverOptions.consumerProperties()
        val topicExists = doesTopicExists(Config.MESSAGE_TOPIC, consumerProperties)
        require(topicExists) { "Topic does not exist: ${Config.MESSAGE_TOPIC}" }

        val topicNames = listOf(Config.MESSAGE_TOPIC)
        val kafkaOptions = receiverOptions.subscription(topicNames)

        kafkaOptions.addAssignListener { partitions: Collection<ReceiverPartition> ->
            partitions.forEach(Consumer { obj: ReceiverPartition -> obj.seekToBeginning() })
        }


        // More options can be added here if needed
        return kafkaOptions
    }

    companion object {
        fun doesTopicExists(
            topicName: String, properties: Map<String, Any>?
        ): Boolean {
            AdminClient.create(properties).use { admin ->
                return admin.listTopics().names().get().stream().anyMatch { topic: String ->
                    topic.equals(
                        topicName, ignoreCase = true
                    )
                }
            }
        }
    }
}
