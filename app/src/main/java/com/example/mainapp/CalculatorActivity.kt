package com.example.mainapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        val tvReceivedData = findViewById<TextView>(R.id.tvReceivedData)
        val data = intent.getStringExtra("EXTRA_DATA")
        tvReceivedData.text = data

        val etNumber1 = findViewById<EditText>(R.id.etNumber1)
        val etNumber2 = findViewById<EditText>(R.id.etNumber2)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnSubtract = findViewById<Button>(R.id.btnSubtract)
        val btnMultiply = findViewById<Button>(R.id.btnMultiply)
        val btnDivide = findViewById<Button>(R.id.btnDivide)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnAdd.setOnClickListener {
            calculate(etNumber1, etNumber2, tvResult, "Add")
        }

        btnSubtract.setOnClickListener {
            calculate(etNumber1, etNumber2, tvResult, "Subtract")
        }

        btnMultiply.setOnClickListener {
            calculate(etNumber1, etNumber2, tvResult, "Multiply")
        }

        btnDivide.setOnClickListener {
            calculate(etNumber1, etNumber2, tvResult, "Divide")
        }
    }

    private fun calculate(etNumber1: EditText, etNumber2: EditText, tvResult: TextView, operation: String) {
        val number1 = etNumber1.text.toString().toDoubleOrNull()
        val number2 = etNumber2.text.toString().toDoubleOrNull()

        if (number1 != null && number2 != null) {
            val result = when (operation) {
                "Add" -> number1 + number2
                "Subtract" -> number1 - number2
                "Multiply" -> number1 * number2
                "Divide" -> {
                    if (number2 != 0.0) {
                        number1 / number2
                    } else {
                        Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                        null
                    }
                }
                else -> null
            }
            result?.let {
                tvResult.text = getString(R.string.result_text, it.toString())
            }
        } else {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show()
        }
    }
}
