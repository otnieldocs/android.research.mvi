package com.otnieldocs.mvi

class StoreFactory<S : State>(
    initialState: S,
    private val reducer: Reducer<S>
) : MviStore<S> {
    private val subscribers = mutableListOf<Subscriber<S>>()

    private var currentState: S = initialState

    override fun getState(): S = currentState

    override fun dispatch(action: MviAction<S>) {
        val newState = reducer(currentState, action)

        if (newState == currentState) return

        currentState = newState
        subscribers.forEach {
            it(currentState)
        }
    }

    override fun subscribe(subscriber: Subscriber<S>) {
        subscribers.add(subscriber.also { it(currentState) })
    }

    override fun unsubscribe(subscriber: Subscriber<S>) {
        subscribers.remove(subscriber)
    }

    override fun unsubscribeAll() {
        subscribers.clear()
    }
}