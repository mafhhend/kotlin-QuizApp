package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
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
    private var SelectedOption:Int=0;
    private var ReadyToNext: Boolean = false;
    private var isDone:Boolean=false;
    private var CountCorrectAnswers: Int = 0;
    private var UserName:String?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        UserName=intent.getStringExtra(Constants.USER_NAME)
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

            if(SelectedOption === 0) return@setOnClickListener;
            if(isDone){

                if(SelectedOption == Constants.getQuestions()[currentPosition-1].correctAnswer)
                {
                    CountCorrectAnswers++;
                    Log.d("score", CountCorrectAnswers.toString())
                }
                var Intent = Intent(this, ResultActivity::class.java);
                Intent.putExtra(Constants.USER_NAME, UserName.toString())
                Intent.putExtra(Constants.CORRECT_ANSWERS, CountCorrectAnswers)
                Intent.putExtra(Constants.TOTAL_QUESTIONS, Constants.getQuestions().size)
                startActivity(Intent);
            }
            if (currentPosition <= Constants.getQuestions().size) {

                if(SelectedOption != Constants.getQuestions()[currentPosition -1].correctAnswer){
                    AnswerView(SelectedOption,R.drawable.wrong_option_border_bg)
                }
                AnswerView(Constants.getQuestions()[currentPosition -1].correctAnswer,R.drawable.correct_option_border_bg)
                if(currentPosition == Constants.getQuestions().size)
                {
                    btnSubmit.text="FINISH"
                    isDone=true;
                }else{
                    btnSubmit.text = "GO TO NEXT QUESTION :) ";
                }
                if(ReadyToNext) {
                    if(SelectedOption == Constants.getQuestions()[currentPosition-1].correctAnswer)
                    {
                        CountCorrectAnswers++;
                        Log.d("score", CountCorrectAnswers.toString())
                    }
                    currentPosition++;
                    assisnValuesToActivity();
                    btnSubmit.text="SUBMIT";
                }
                ReadyToNext=true;

            };
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
        SelectOptionView(v as TextView);
    }

    private fun SelectOptionView(textView: TextView){
        textView.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)

        textView.setTextColor(Color.parseColor("#202020"))

        when(textView.id){
            R.id.tv_option_one->SelectedOption=1
            R.id.tv_option_two->SelectedOption=2
            R.id.tv_option_three->SelectedOption=3
            R.id.tv_option_four->SelectedOption=4
        }
    }

    private fun AnswerView(answer: Int, drawableView: Int) {
        println("$answer");
        when (answer) {
            1 -> {
                tvOptionOne.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                tvOptionTwo.background = ContextCompat.getDrawable(this, drawableView)
            }
            3 -> {
                tvOptionThree.background = ContextCompat.getDrawable(this, drawableView)
            }
            4 -> {
                tvOptionFour.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }

    private fun resetSelectedOption(){
        ReadyToNext=false;
        SelectedOption=0;
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