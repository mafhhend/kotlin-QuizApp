package com.example.quizapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var btn_start:Button
    private lateinit var et_name:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        if(Build.VERSION.SDK_INT >=30){
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }

        btn_start = findViewById(R.id.btn_start)
        et_name = findViewById(R.id.et_name)

        btn_start.setOnClickListener {
            if(et_name.text.toString().isEmpty()){
                Toast.makeText(this,"Please enter your name",Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this, QuizQuestionsActivity::class.java);
                startActivity(intent)
                finish() //close current activity
             }
        }


    }
}