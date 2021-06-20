package com.rsschool.quiz

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.rsschool.quiz.databinding.ActivityQuiz3Binding
import java.util.ArrayList

class ThrActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuiz3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuiz3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val arrFromFourthActivity = intent.getIntArrayExtra(TRANSMITTED_ID_4_to_3)
        val arrayFromPrevAnswers = intent.getIntArrayExtra(TRANSMITTED_ID_2_to_3)
        val arrayStringAnswers = intent.getStringArrayListExtra(TRANSMITTED_STRING_2_to_3)
        val arrayAnswers = arrFromFourthActivity ?: arrayFromPrevAnswers

        if (arrayAnswers?.get(2) ?: 0 > 0) {
            binding.radioGroup.check(arrayAnswers?.get(2)!!)
            binding.nextButton.isEnabled = true
        }

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId -> if (checkedId != 0) {
            binding.nextButton.isEnabled = true
            arrayAnswers?.set(2, binding.radioGroup.checkedRadioButtonId)
        } }

        binding.previousButton.setOnClickListener { onClickPrevious(arrayAnswers) }
        binding.nextButton.setOnClickListener {
            arrayStringAnswers?.set(2, findViewById<RadioButton>(binding.radioGroup.checkedRadioButtonId).text.toString())
            onClickNext(arrayAnswers, arrayStringAnswers) }
        binding.toolbar.setOnClickListener { onClickPrevious(arrayAnswers) }
    }

    private fun onClickPrevious(arrToTrans: IntArray?) {
        val intent = Intent(this, SecActivity::class.java)
        intent.putExtra(SecActivity.TRANSMITTED_ID_3_to_2, arrToTrans)
        startActivity(intent)
    }

    private fun onClickNext(arrToTrans: IntArray?, arrStringToTrans: ArrayList<String>?) {
        val intent = Intent(this, FourthActivity::class.java)
        intent.putExtra(FourthActivity.TRANSMITTED_ID_3_to_4, arrToTrans)
        intent.putExtra(FourthActivity.TRANSMITTED_STRING_3_to_4, arrStringToTrans)
        startActivity(intent)
    }

    companion object{
        const val TRANSMITTED_ID_2_to_3 = "transmittedIdRadioButton"
        const val TRANSMITTED_STRING_2_to_3 = "transmittedStringRadioButton"
        const val TRANSMITTED_ID_4_to_3 = "transmittedIfFromNext"
    }

}