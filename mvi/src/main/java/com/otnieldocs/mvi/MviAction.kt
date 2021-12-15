package com.otnieldocs.mvi

sealed class MviAction<out S:State> : Action {
    object Uninitialised: MviAction<Nothing>()
    object Initialised: MviAction<Nothing>()
    data class Updated<S: State>(val state: S): MviAction<S>()
}