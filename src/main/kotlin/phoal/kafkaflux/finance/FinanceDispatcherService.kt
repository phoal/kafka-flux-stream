package phoal.kafkaflux.finance

import org.springframework.stereotype.Service
import phoal.kafkaflux.finance.dispatcher.FinanceDispatcher

@Service
class FinanceDispatcherService(dispatchers: Set<FinanceDispatcher>) {

}