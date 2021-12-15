package com.otnieldocs.mvi

interface Action

interface State

typealias Reducer<S> = (S, MviAction<S>) -> S

typealias Subscriber<S> = (S) -> Unit