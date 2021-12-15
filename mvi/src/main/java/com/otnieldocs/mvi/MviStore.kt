package com.otnieldocs.mvi

interface MviStore<S: State> {
    fun getState(): S

    fun dispatch(action: MviAction<S>)

    fun subscribe(subscriber: Subscriber<S>)

    fun unsubscribe(subscriber: Subscriber<S>)

    fun unsubscribeAll()
}