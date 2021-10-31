package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider


private const val KEY_INDEX="index"
private const val Key_question="Question"
const val EXTRA_ANSWER_IS_TRU_OR_FALSE="main,activity"
const val EXTRA_SHOW_QUESTION="Question"
private const val REQUEST_CODE_CHEAT=0
class MainActivity : AppCompatActivity() {

    private lateinit var falseButton: Button
    private lateinit var trueButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView
    private lateinit var preButton:Button
    private lateinit var textView:TextView
    private lateinit var cheatButton:Button

    val TAG ="MainActivity"
    private val quizViewModel by lazy { ViewModelProvider(this ).get(QuizViewModel::class.java)}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode!= Activity.RESULT_OK){
            return
        }
        if (requestCode== REQUEST_CODE_CHEAT){
            quizViewModel.isCheater=data?.getBooleanExtra(EXTRA_ANSWER_SHOWN,false)?:false
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG,"onCreate()")
        Log.d(TAG,"Hi i am view model from main activity $quizViewModel")

        val currentIndex= savedInstanceState?.getInt(KEY_INDEX)?:0
        Log.d(TAG,"onCreate")
        quizViewModel.currentIndex=currentIndex






        /* val provider=ViewModelProvider(this )
         val quizViewModel=provider.get(QuizViewModel::class.java)
         Log.d(TAG,"Hi i am view model from main activity $quizViewModel")

         */


        falseButton=findViewById(R.id.false_button)
        trueButton=findViewById(R.id.true_button)
        nextButton=findViewById(R.id.next_question)
        questionTextView=findViewById(R.id.question_Tv)
        preButton=findViewById(R.id.pre_question)
        textView=findViewById(R.id.textView)
        cheatButton=findViewById(R.id.cheat_btn)

        cheatButton.setOnClickListener {

            val intent=Intent(this,CheatnActivity::class.java)
            intent.putExtra(EXTRA_ANSWER_IS_TRU_OR_FALSE,quizViewModel.currentQuestionAnswer)
            intent.putExtra(EXTRA_SHOW_QUESTION,quizViewModel.currentQuestionText)
            startActivityForResult(intent, REQUEST_CODE_CHEAT)
        }



        falseButton.setOnClickListener {
            checkAnswer(false)
            Toast.makeText(this, R.string.inCorrect_toast, Toast.LENGTH_SHORT).show()
        }
        trueButton.setOnClickListener {
            checkAnswer(true)
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show()


        }
        /*preButton.setOnClickListener {
            quizViewModel.preQuestion()
            updateQuestion()
            updateWrite()

        }

         */
        preButton.setOnClickListener {
            quizViewModel.preQuestion()
            updateQuestion()
            updateWrite()


        }
        nextButton.setOnClickListener {
            quizViewModel.nextQuestion()
            updateQuestion()
            updateWrite()
        }


        updateQuestion()
        updateWrite()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG,"a value has been saved")
        outState.putInt(KEY_INDEX,quizViewModel.currentIndex)
        //outState.putInt(KEY_INDEX,quizViewModel.currentQuestionText)
    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"onRestart()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy()")
    }

    private fun updateQuestion(){
        val questionTexRestId=quizViewModel.currentQuestionText
        questionTextView.setText( questionTexRestId)
    }
    private fun updateWrite(){
        val currentTextVw=quizViewModel.currentTextView
        textView.setText(currentTextVw)
    }

    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer =quizViewModel.currentQuestionAnswer

        val toastMassage=when{
            quizViewModel.isCheater->R.string.cheater_toast
            userAnswer==correctAnswer->R.string.correct_toast
            else->R.string.inCorrect_toast
        }
        Toast.makeText(this, toastMassage, Toast.LENGTH_SHORT).show()


    }
}
/*   if (userAnswer==correctAnswer){
       Toast.makeText(this,R.string.correct_toast,Toast.LENGTH_LONG).show()
   }else{
       Toast.makeText(this,R.string.inCorrect_toast,Toast.LENGTH_LONG).show()
   }
}


}

 */