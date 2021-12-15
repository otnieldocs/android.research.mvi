package com.otnieldocs.androidresearchmvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.otnieldocs.mvi.MviViewModel

class MainViewModel(initialState: MainState): MviViewModel<MainState>(initialState) {

    fun increase() {
        setState {
            it.copy(value = it.value.inc())
        }
    }

    fun decrease() {
        setState {
            it.copy(value = it.value.dec())
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val initialState: MainState): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(initialState) as T
        }
    }
}