package com.example.bai1

// src/main/java/com/example/studentsearchapp/MainActivity.kt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.SearchView
import com.example.bai1.R
import com.example.bai1.Student
import com.example.bai1.StudentAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var studentAdapter: StudentAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView

    // Sample data
    private val students = listOf(

        Student("Nguyen Van A ", "20215002"),
        Student("Nguyen Van B ", "20215149"),
        Student("Dang Minh Chuc", "20215001"),

        // Add more students as needed
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        searchView = findViewById(R.id.search_view)

        // Set up RecyclerView
        studentAdapter = StudentAdapter(students)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        // Set up SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText)
                return true
            }
        })
    }

    // Filter function to search through the list
    private fun filter(text: String?) {
        val filteredStudents = if (text.isNullOrEmpty() || text.length < 3) {
            students
        } else {
            students.filter {
                it.name.contains(text, ignoreCase = true) || it.id.contains(text)
            }
        }
        studentAdapter.filterList(filteredStudents)
    }
}
