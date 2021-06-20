package com.rsschool.quiz

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.rsschool.quiz.databinding.ActivityQuiz4Binding
import java.util.ArrayList

class FourthActivity: AppCompatActivity() {
    private lateinit var binding: ActivityQuiz4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuiz4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val arrFromFifthActivity = intent.getIntArrayExtra(TRANSMITTED_ID_5_to_4)
        val arrayFromPrevAnswers = intent.getIntArrayExtra(TRANSMITTED_ID_3_to_4)
        val arrayStringAnswers = intent.getStringArrayListExtra(TRANSMITTED_STRING_3_to_4)
        val arrayAnswers = arrFromFifthActivity ?: arrayFromPrevAnswers

        if (arrayAnswers?.get(3) ?: 0 > 0) {
            binding.radioGroup.check(arrayAnswers?.get(3)!!)
            binding.nextButton.isEnabled = true
        }

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId -> if (checkedId != 0) {
            binding.nextButton.isEnabled = true
            arrayAnswers?.set(3, binding.radioGroup.checkedRadioButtonId)
        } }

        binding.previousButton.setOnClickListener { onClickPrevious(arrayAnswers) }
        binding.nextButton.setOnClickListener {
            arrayStringAnswers?.set(3, findViewById<RadioButton>(binding.radioGroup.checkedRadioButtonId).text.toString())
            onClickNext(arrayAnswers, arrayStringAnswers) }
        binding.toolbar.setOnClickListener { onClickPrevious(arrayAnswers) }
    }

    private fun onClickPrevious(arrToTrans: IntArray?) {
        val intent = Intent(this, ThrActivity::class.java)
        intent.putExtra(ThrActivity.TRANSMITTED_ID_4_to_3, arrToTrans)
        startActivity(intent)
    }

    private fun onClickNext(arrToTrans: IntArray?, arrStringToTrans: ArrayList<String>?) {
        val intent = Intent(this, FifthActivity::class.java)
        intent.putExtra(FifthActivity.TRANSMITTED_ID_4_to_5, arrToTrans)
        intent.putExtra(FifthActivity.TRANSMITTED_STRING_4_to_5, arrStringToTrans)
        startActivity(intent)
    }

    companion object{
        const val TRANSMITTED_ID_3_to_4 = "transmittedIdRadioButton"
        const val TRANSMITTED_STRING_3_to_4 = "transmittedStringRadioButton"
        const val TRANSMITTED_ID_5_to_4 = "transmittedIfFromNext"
    }
}