package com.company.nytimes.common.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import artifact.signals_bus.SignalsEmitter
import artifact.signals_bus.contract.Emitter
import com.company.nytimes.common.utils.Signal
import dagger.hilt.android.lifecycle.HiltViewModel

abstract class BaseViewModel(val app: Application) : AndroidViewModel(app), Emitter<Signal> {
    override val signalsEmitter: SignalsEmitter<Signal> = SignalsEmitter(this::class.java.name)
}