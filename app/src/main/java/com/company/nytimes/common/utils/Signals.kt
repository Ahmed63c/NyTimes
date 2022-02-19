package com.company.nytimes.common.utils


import artifact.signals_bus.contract.Navigational
import artifact.signals_bus.contract.Signal as SignalContract

sealed class Signal(override var signature: String? = null) : SignalContract

object Load : Signal()
object StopLoading : Signal()

sealed class SomethingWentWrong : Signal() {
    object Validation : SomethingWentWrong()
    object ConnectionFailure : SomethingWentWrong()
    object ErrorMessage : SomethingWentWrong()
    object Unknown : SomethingWentWrong()
    object UserAlreadyExist : SomethingWentWrong()
}



