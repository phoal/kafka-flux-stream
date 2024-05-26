package phoal.kafkaflux

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaFluxStreamApplication

fun main(args: Array<String>) {
	runApplication<KafkaFluxStreamApplication>(*args)
}
