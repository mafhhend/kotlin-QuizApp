package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.quizapp.Constants

class ResultActivity : AppCompatActivity() {
    private lateinit var tv_name:TextView;
    private lateinit var tv_score:TextView;
    private lateinit var btn_finish: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        tv_name = findViewById(R.id.tv_name)
        tv_score = findViewById(R.id.tv_score)
        val score = intent.getIntExtra(Constants.CORRECT_ANSWERS    , 0);
        val maximumScore=intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        btn_finish = findViewById(R.id.btn_finish)
        tv_name.text=intent.getStringExtra(Constants.USER_NAME)
        tv_score.text="Your Score is $score out of $maximumScore"

    }
}