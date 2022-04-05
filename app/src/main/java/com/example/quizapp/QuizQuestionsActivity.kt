package com.example.quizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.quizapp.Constants

class QuizQuestionsActivity : AppCompatActivity(),View.OnClickListener {
    private var currentPosition:Int=1;
    private lateinit var tvOptionOne:TextView;
    private lateinit var tvOptionTwo:TextView;
    private lateinit var tvOptionThree:TextView;
    private lateinit var tvOptionFour:TextView;
    private lateinit var btnSubmit:Button;
    private lateinit var progressBar:ProgressBar;
    private lateinit var tv_progress:TextView;
    private lateinit var tvImageView:ImageView;

    //    private var mCurrentPosition: Int = 1;
    private var mSelectedOptionPosition: Int = 0;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        tvImageView=findViewById(R.id.iv_image)
        tvOptionOne=findViewById(R.id.tv_option_one)
        tvOptionTwo=findViewById(R.id.tv_option_two)
        tvOptionThree=findViewById(R.id.tv_option_three)
        tvOptionFour=findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)
        progressBar = findViewById(R.id.progressBar)
        tv_progress = findViewById(R.id.tv_progress)

        tvOptionOne.setOnClickListener(this);
        tvOptionTwo.setOnClickListener(this);
        tvOptionThree.setOnClickListener(this);
        tvOptionFour.setOnClickListener(this);

        assisnValuesToActivity();

        btnSubmit.setOnClickListener {
            currentPosition++;
            assisnValuesToActivity();
        }

    }
        private fun assisnValuesToActivity(){
            resetSelectedOption()
        for(question in Constants.getQuestions()){
            if(currentPosition==question.id){
                tvOptionOne.text=question.optionOne;
                tvOptionTwo.text=question.optionTwo;
                tvOptionThree.text=question.optionThree;
                tvOptionFour.text=question.optionFour;
                progressBar.progress=currentPosition;
                tv_progress.text="$currentPosition/10";
                tvImageView.setImageResource(question.image)
            }
        }
    }

    override fun onClick(v: View?) {
        resetSelectedOption()
        val textViewSelected:TextView=(v as TextView);
        textViewSelected.background =
            ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
        textViewSelected.setTextColor(Color.parseColor("#202020"))
    }

    private fun resetSelectedOption(){
        val options = ArrayList<TextView>();
        options.add(0,tvOptionOne)
        options.add(1,tvOptionTwo)
        options.add(2,tvOptionThree)
        options.add(3, tvOptionFour)
        for (option in options){
            option.typeface= Typeface.DEFAULT;
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
            option.setTextColor(Color.parseColor("#7A8089"))
        }
    }
}