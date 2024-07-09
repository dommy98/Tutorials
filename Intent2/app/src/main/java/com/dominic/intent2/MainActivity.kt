package com.dominic.intent2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val rvTodos: RecyclerView = findViewById(R.id.rvTodos)
        val btnAddTodo: Button = findViewById(R.id.btnAddTodo)
        var todoList = mutableListOf(
            Todo("Follow AndroidDevs", false),
            Todo("Learn About Recyclerview", true),
            Todo("Feed my cat", false),
            Todo("prank my boss", true),
            Todo("Take shower", false)

        )
        val adapter = TodoAdapter(todoList)
        rvTodos.adapter = adapter
        rvTodos.layoutManager = LinearLayoutManager(this)

        btnAddTodo.setOnClickListener {
            val etTodo: EditText = findViewById(R.id.etTodo)
            val title = etTodo.text.toString()
            val todo = Todo(title, false)
            todoList.add(todo)
            adapter.notifyItemInserted(todoList.size -1)
        }
    }
}