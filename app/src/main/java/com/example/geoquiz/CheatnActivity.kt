package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

const val EXTRA_ANSWER_SHOWN="you are cheater!!"
class CheatnActivity : AppCompatActivity() {

    var answerIsTrueOrFalse=false
    var questionShow=0
private lateinit var question_tv:TextView
    private lateinit var answerTv:TextView
    private lateinit var showAnswerButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheatn)
        question_tv=findViewById(R.id.queeston_tv)
        answerTv=findViewById(R.id.answer_tv)
        showAnswerButton=findViewById(R.id.show_answer_button)
        answerIsTrueOrFalse=intent.getBooleanExtra(EXTRA_ANSWER_IS_TRU_OR_FALSE,false)

        questionShow=intent.getIntExtra(EXTRA_SHOW_QUESTION,0)

        showAnswerButton.setOnClickListener {
            answerTv.setText(answerIsTrueOrFalse.toString())

            setAnswerShownResult()

            question_tv.setText(questionShow)
        }


    }
    fun setAnswerShownResult(){
        val data=Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN,true)
        }
        setResult(Activity.RESULT_OK,data)
    }
}