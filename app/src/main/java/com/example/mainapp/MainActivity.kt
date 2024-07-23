package com.example.mainapp



import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnBudgetTracker = findViewById<Button>(R.id.btnBudgetTracker)
        val btnCalculator = findViewById<Button>(R.id.btnCalculator)

        btnBudgetTracker.setOnClickListener {
            val intent = Intent(this, BudgetTrackerActivity::class.java)
            intent.putExtra("EXTRA_DATA", "Data from MainActivity to Budget Tracker")
            startActivity(intent)
        }

        btnCalculator.setOnClickListener {
            val intent = Intent(this, CalculatorActivity::class.java)
            intent.putExtra("EXTRA_DATA", "Data from MainActivity to Calculator")
            startActivity(intent)
        }
    }
}
