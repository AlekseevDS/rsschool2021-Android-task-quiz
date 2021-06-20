package com.rsschool.quiz

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.rsschool.quiz.databinding.ActivityQuiz2Binding
import java.util.*

class SecActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuiz2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuiz2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val arrFromTrdActivity = intent.getIntArrayExtra(TRANSMITTED_ID_3_to_2)
        val arrayFromPrevAnswers = intent.getIntArrayExtra(TRANSMITTED_ID_1_to_2)
        val arrayStringAnswers = intent.getStringArrayListExtra(TRANSMITTED_STRING_1_to_2)
        val arrayAnswers = arrFromTrdActivity ?: arrayFromPrevAnswers

        if (arrayAnswers?.get(1) ?: 0 > 0) {
            binding.radioGroup.check(arrayAnswers?.get(1)!!)
            binding.nextButton.isEnabled = true
        }

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId -> if (checkedId != 0) {
            binding.nextButton.isEnabled = true
            arrayAnswers?.set(1, binding.radioGroup.checkedRadioButtonId)
        } }


        binding.previousButton.setOnClickListener { onClickPrevious(arrayAnswers) }
        binding.nextButton.setOnClickListener {
            arrayStringAnswers?.set(1, findViewById<RadioButton>(binding.radioGroup.checkedRadioButtonId).text.toString())
            onClickNext(arrayAnswers, arrayStringAnswers) }
        binding.toolbar.setOnClickListener { onClickPrevious(arrayAnswers) }
    }

    private fun onClickPrevious(arrToTrans: IntArray?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(MainActivity.TRANSMITTED_ID_2_to_1, arrToTrans)
        startActivity(intent)
    }

    private fun onClickNext(arrToTrans: IntArray?, arrStringToTrans: ArrayList<String>?) {
        val intent = Intent(this, ThrActivity::class.java)
        intent.putExtra(ThrActivity.TRANSMITTED_ID_2_to_3, arrToTrans)
        intent.putExtra(ThrActivity.TRANSMITTED_STRING_2_to_3, arrStringToTrans)
        startActivity(intent)
    }

    companion object{
        const val TRANSMITTED_ID_1_to_2 = "transmittedIdRadioButton"
        const val TRANSMITTED_STRING_1_to_2 = "transmittedStringRadioButton"
        const val TRANSMITTED_ID_3_to_2 = "transmittedIfFromNext"

    }

}