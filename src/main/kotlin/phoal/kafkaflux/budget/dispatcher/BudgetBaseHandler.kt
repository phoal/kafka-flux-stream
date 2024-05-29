package phoal.kafkaflux.budget.dispatcher

import phoal.kafkaflux.core.EventType
import phoal.kafkaflux.core.Handler

interface BudgetBaseHandler<T: Any>: Handler<T>
