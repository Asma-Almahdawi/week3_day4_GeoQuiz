package com.example.geoquiz
import androidx.lifecycle.ViewModel
import android.util.Log

private const val TAQ="QuizViewModel"


class QuizViewModel:ViewModel() {


    private val questionBank= listOf(
        Question(R.string.first_question,false,R.string.first_write),
        Question(R.string.second_question,false,R.string.Ssecend_write),
        Question(R.string.third_question,true,R.string.third_write),
        Question(R.string.forth_question,true,R.string.forth_write)

    )
    var currentIndex=0

    var  isCheater=false

    val currentQuestionText:Int
        get() = questionBank[currentIndex].textResId

    val currentQuestionAnswer:Boolean
        get()=questionBank[currentIndex].answer
    val currentTextView:Int
        get()=questionBank[currentIndex].writeQuestion







    fun preQuestion(){
        currentIndex=(currentIndex-1)% questionBank.size
    }
    fun nextQuestion(){

        currentIndex=(currentIndex+1)% questionBank.size


    }


    init {
        Log.d(TAQ,"Hi i am from the view model class")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAQ,"\$_$ yoe are onCleared")
    }
}