package com.example.calculatorui_constraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var calculatedResult:TextView
    private lateinit var inputNumbers:TextView
    private lateinit var buttonClear:Button
    private lateinit var buttonDivide:Button
    private lateinit var buttonMultiply:Button
    private lateinit var buttonMinus:Button
    private lateinit var buttonPlus:Button
    private lateinit var buttonMod:Button
    private lateinit var buttonResult:Button
    private var firstNumber:Double=0.0
    private var currentOperation:Operations?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        addCallBacks()

    }

    private fun addCallBacks() {
        buttonClear.setOnClickListener {
            clear()
        }
        buttonPlus.setOnClickListener {
            preparation(Operations.Plus)
        }
        buttonMinus.setOnClickListener {
            preparation(Operations.Minus)
        }
        buttonDivide.setOnClickListener {
            preparation(Operations.Division)
        }
        buttonMultiply.setOnClickListener {
            preparation(Operations.Multiply)
        }
        buttonMod.setOnClickListener {
            preparation(Operations.Mod)
        }

        buttonResult.setOnClickListener {
            val finalResult =doOperation()
            calculatedResult.text=finalResult.toString()
        }

    }
    private fun doOperation():Double {
        val secondNumber=inputNumbers.text.toString().toDouble()
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
        firstNumber=inputNumbers.text.toString().toDouble()
        clear()
        currentOperation=operation

    }
    private fun clear() {
        inputNumbers.text= ""
        calculatedResult.text=""
    }
    private fun initViews() {
        calculatedResult=findViewById(R.id.result)
        inputNumbers=findViewById(R.id.input_values)
        buttonClear=findViewById(R.id.clear_button)
        buttonDivide=findViewById(R.id.division_button)
        buttonMultiply=findViewById(R.id.multiply_button)
        buttonMinus=findViewById(R.id.minus_button)
        buttonPlus=findViewById(R.id.plus_button)
        buttonResult=findViewById(R.id.equal_button)
        buttonMod=findViewById(R.id.mod_button)
    }
    fun getInputNumbers(view: View) {
        val newDigit=( view as Button).text.toString()
        val oldDigits=inputNumbers.text.toString()
        val number=newDigit+oldDigits
        inputNumbers.text=number
    }
}

