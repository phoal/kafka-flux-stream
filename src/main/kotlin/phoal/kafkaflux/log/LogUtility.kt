package phoal.kafkaflux.log

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.logging.Logger

@Configuration
class LogUtility {
    @Bean
    fun logger(): Logger {
        return Logger.getAnonymousLogger()
    }
}