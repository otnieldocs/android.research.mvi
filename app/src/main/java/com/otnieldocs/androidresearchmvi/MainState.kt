package com.otnieldocs.androidresearchmvi

import com.otnieldocs.mvi.State

data class MainState(
    val value: Int = 0,
    val name: String = "John Doe"
): State