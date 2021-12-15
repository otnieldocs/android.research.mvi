package com.otnieldocs.androidresearchmvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels { MainViewModel.Factory(MainState()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvName = findViewById<TextView>(R.id.tvHelloWorld)
        val tvCounter = findViewById<TextView>(R.id.tvCounter)
        val btnInc = findViewById<Button>(R.id.btnIncrement)
        val btnDec = findViewById<Button>(R.id.btnDecrement)

        viewModel.subscribe {
            tvCounter.text = "${it.value}"
        }

        viewModel.subscribe {
            tvName.text = it.name
        }

        btnInc.setOnClickListener {
            viewModel.increase()
        }

        btnDec.setOnClickListener {
            viewModel.decrease()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        viewModel.unsubscribeAll()
    }
}