package com.otnieldocs.mvi

import androidx.lifecycle.ViewModel

open class MviViewModel<S : State>(
    initialState: S
) : ViewModel() {

    private val currentState = initialState

    private val reducer: Reducer<S> = { oldState: S, action: MviAction<S> ->
        when (action) {
            is MviAction.Initialised -> currentState
            is MviAction.Updated -> action.state
            else -> oldState
        }
    }

    private val store = StoreFactory(initialState = currentState, reducer = reducer)

    init {
        store.dispatch(MviAction.Initialised)
    }

    override fun onCleared() {
        super.onCleared()

        store.unsubscribeAll()
    }

    fun subscribe(bind: (S) -> Unit) {
        store.subscribe {
            bind.invoke(store.getState())
        }
    }

    fun withState(onState: (S) -> Unit) {
        onState.invoke(store.getState())
    }

    fun setState(newState: (S) -> S) {
        store.dispatch(action = MviAction.Updated(state = newState.invoke(store.getState())))
    }

    fun unsubscribeAll() {
        store.unsubscribeAll()
    }
}