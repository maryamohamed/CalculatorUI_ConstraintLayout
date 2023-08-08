package com.example.calculatorui_constraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.calculatorui_constraintlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var firstNumber:Double=0.0
    var currentOperation:Operations?=null
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addCallBacks()

    }

    private fun addCallBacks() {
        binding.clearButton.setOnClickListener {
            clear()
        }
        binding.plusButton.setOnClickListener {
            preparation(Operations.Plus)
        }
        binding.minusButton.setOnClickListener {
            preparation(Operations.Minus)
        }
        binding.divisionButton.setOnClickListener {
            preparation(Operations.Division)
        }
        binding.multiplyButton.setOnClickListener {
            preparation(Operations.Multiply)
        }
        binding.modButton.setOnClickListener {
            preparation(Operations.Mod)
        }

        binding.equalButton.setOnClickListener {
            val finalResult =doOperation()
            binding.result.text=finalResult.toString()
        }

    }
    private fun doOperation():Double {
        val secondNumber=binding.inputValues.text.toString().toDouble()
        return when(currentOperation){
            Operations.Plus -> firstNumber.plus(secondNumber)
            Operations.Minus -> firstNumber.minus(secondNumber)
            Operations.Multiply -> firstNumber.times(secondNumber)
            Operations.Division -> firstNumber.div(secondNumber)
            Operations.Mod -> firstNumber.mod(secondNumber)
            null -> 0.0
        }
    }
    private fun preparation(operation: Operations){
        firstNumber=binding.inputValues.text.toString().toDouble()
        clear()
        currentOperation=operation

    }
    private fun clear() {
        binding.inputValues.text= ""
        binding.result.text=""
    }

    fun getInputNumbers(view: View) {
        val newDigit=( view as Button).text.toString()
        val oldDigits=binding.inputValues.text.toString()
        val number=newDigit+oldDigits
        binding.inputValues.text=number
    }
}

