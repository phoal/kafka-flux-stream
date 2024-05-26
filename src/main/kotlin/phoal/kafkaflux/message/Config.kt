package phoal.kafkaflux.message

object Config {
    const val BOOTSTRAP_SERVERS_CONFIG = "localhost:9092"
    const val CONSUMER_GROUP_ID_CONFIG = "phoal.kafkaflux.consumer"
    const val MESSAGE_TOPIC = "kafka-message"
    const val AUTO_OFFSET_RESET_CONFIG = "earliest"
}