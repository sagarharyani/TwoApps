package com.example.mainapp

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BudgetTrackerActivity : AppCompatActivity() {

    private var selectedCategory: String? = null
    private var totalExpense: Double = 0.0
    private val expenseList = mutableListOf<Expense>()
    private lateinit var expenseAdapter: ExpenseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget_tracker)

        val tvReceivedData = findViewById<TextView>(R.id.tvReceivedData)
        val data = intent.getStringExtra("EXTRA_DATA")
        tvReceivedData.text = data

        val etAmount = findViewById<EditText>(R.id.etAmount)
        val spinnerCategory = findViewById<Spinner>(R.id.spinnerCategory)
        val btnAddExpense = findViewById<Button>(R.id.btnAddExpense)
        val tvTotalExpense = findViewById<TextView>(R.id.tvTotalExpense)
        val recyclerViewExpenses = findViewById<RecyclerView>(R.id.recyclerViewExpenses)

        // Set up Spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.expense_categories,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCategory.adapter = adapter
        }

        spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedCategory = parent.getItemAtPosition(position) as String
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedCategory = null
            }
        }

        btnAddExpense.setOnClickListener {
            val amountStr = etAmount.text.toString()
            if (amountStr.isNotEmpty() && selectedCategory != null) {
                val amount = amountStr.toDouble()
                totalExpense += amount
                tvTotalExpense.text = getString(R.string.total_expense, totalExpense.toString())
                etAmount.text.clear()

                // Add expense to the list and update the RecyclerView
                val expense = Expense(amount, selectedCategory!!)
                expenseList.add(expense)
                expenseAdapter.notifyDataSetChanged()

                Toast.makeText(this, "Expense added in $selectedCategory category", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter amount and select category", Toast.LENGTH_SHORT).show()
            }
        }

        // Set up RecyclerView
        expenseAdapter = ExpenseAdapter(expenseList)
        recyclerViewExpenses.adapter = expenseAdapter
        recyclerViewExpenses.layoutManager = LinearLayoutManager(this)
    }
}