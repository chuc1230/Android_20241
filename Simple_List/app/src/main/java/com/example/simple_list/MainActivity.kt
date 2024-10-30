package com.example.simple_list

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioEven: RadioButton
    private lateinit var radioOdd: RadioButton
    private lateinit var radioSquare: RadioButton
    private lateinit var buttonShow: Button
    private lateinit var textViewError: TextView
    private lateinit var listViewResults: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khởi tạo các thành phần
        editTextNumber = findViewById(R.id.editTextNumber)
        radioGroup = findViewById(R.id.radioGroup)
        radioEven = findViewById(R.id.radioEven)
        radioOdd = findViewById(R.id.radioOdd)
        radioSquare = findViewById(R.id.radioSquare)
        buttonShow = findViewById(R.id.buttonShow)
        textViewError = findViewById(R.id.textViewError)
        listViewResults = findViewById(R.id.listViewResults)

        buttonShow.setOnClickListener {
            textViewError.visibility = View.GONE  // Ẩn thông báo lỗi

            val input = editTextNumber.text.toString().trim()

            if (input.isEmpty()) {
                textViewError.text = "Vui lòng nhập số nguyên dương!"
                textViewError.visibility = View.VISIBLE
                return@setOnClickListener
            }

            val n = input.toIntOrNull()
            if (n == null || n <= 0) {
                textViewError.text = "Vui lòng nhập số nguyên dương hợp lệ!"
                textViewError.visibility = View.VISIBLE
                return@setOnClickListener
            }

            val resultList = ArrayList<Int>()

            when {
                radioEven.isChecked -> {
                    for (i in 0..n step 2) {
                        resultList.add(i)
                    }
                }
                radioOdd.isChecked -> {
                    for (i in 1..n step 2) {
                        resultList.add(i)
                    }
                }
                radioSquare.isChecked -> {
                    for (i in 0..n) {
                        val square = i * i
                        if (square > n) break
                        resultList.add(square)
                    }
                }
                else -> {
                    textViewError.text = "Vui lòng chọn một loại số!"
                    textViewError.visibility = View.VISIBLE
                    return@setOnClickListener
                }
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
            listViewResults.adapter = adapter
        }
    }
}
