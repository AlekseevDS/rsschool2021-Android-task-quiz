package com.rsschool.quiz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rsschool.quiz.databinding.ActivityFinal6Binding

class SixActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFinal6Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinal6Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val arrayStringAnswers = intent.getStringArrayListExtra(TRANSMITTED_STRING_5_to_6)
        val arrayStringRightAnswers = arrayListOf(
            getString(R.string.answer_1_3_right),
            getString(R.string.answer_2_1_right),
            getString(R.string.answer_3_4_right),
            getString(R.string.answer_4_2_right),
            getString(R.string.answer_5_3_right))
        var counterOfRightAnswers = 0

        if(arrayStringAnswers!=null){
            for((i,j)in(0..4).zip(0..4)){
                if(arrayStringAnswers[i]==arrayStringRightAnswers[j])
                counterOfRightAnswers++
            }
        }

        val message = """
            Quiz Results
            Your result: ${ (counterOfRightAnswers.toDouble() / 5 * 100).toInt() } %
            
            1) ${getString(R.string.question_1)}
            Your answer: ${arrayStringAnswers?.get(0)}
            
            2) ${getString(R.string.question_2)}
            Your answer: ${arrayStringAnswers?.get(1)}
            
            3) ${getString(R.string.question_3)}
            Your answer: ${arrayStringAnswers?.get(2)}
            
            4) ${getString(R.string.question_4)}
            Your answer: ${arrayStringAnswers?.get(3)}
            
            5) ${getString(R.string.question_5)}
            Your answer: ${arrayStringAnswers?.get(4)}
        """.trimIndent()

        binding.shareButton.setOnClickListener { onClickShare(message) }
        binding.restartButton.setOnClickListener { onClickRestart() }
        binding.closeButton.setOnClickListener { onClickClose() }

        binding.resultPercentTextView.text = "${ (counterOfRightAnswers.toDouble() / 5 * 100).toInt() } %"
    }

    fun onClickShare(message: String) {
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.setType("text/plain")
        emailIntent.putExtra(Intent.EXTRA_TEXT, message)
        startActivity(emailIntent)
    }

    fun onClickRestart() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun onClickClose() {
        this.finishAffinity()
    }

    companion object{
        const val TRANSMITTED_ID_5_to_6 = "transmittedIdRadioButton"
        const val TRANSMITTED_STRING_5_to_6 = "transmittedStringRadioButton"

    }

    override fun onBackPressed() {
        onClickRestart()
    }

}