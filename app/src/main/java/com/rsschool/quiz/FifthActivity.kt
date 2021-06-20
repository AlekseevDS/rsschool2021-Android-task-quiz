package com.rsschool.quiz

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.rsschool.quiz.databinding.ActivityQuiz5Binding
import java.util.ArrayList

class FifthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuiz5Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuiz5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val arrayFromPrevAnswers = intent.getIntArrayExtra(TRANSMITTED_ID_4_to_5)
        val arrayStringAnswers = intent.getStringArrayListExtra(TRANSMITTED_STRING_4_to_5)
        val arrayAnswers = arrayFromPrevAnswers

        if (arrayAnswers?.get(4) ?: 0 > 0) {
            binding.radioGroup.check(arrayAnswers?.get(4)!!)
            binding.submitButton.isEnabled = true
        }

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId -> if (checkedId != 0) {
            binding.submitButton.isEnabled = true
            arrayAnswers?.set(4, binding.radioGroup.checkedRadioButtonId)
        } }

        binding.previousButton.setOnClickListener { onClickPrevious(arrayAnswers) }
        binding.submitButton.setOnClickListener {
            arrayStringAnswers?.set(4, findViewById<RadioButton>(binding.radioGroup.checkedRadioButtonId).text.toString())
            onClickSubmit(arrayAnswers, arrayStringAnswers) }
        binding.toolbar.setOnClickListener { onClickPrevious(arrayAnswers) }
    }

    private fun onClickPrevious(arrToTrans: IntArray?) {
        val intent = Intent(this, FourthActivity::class.java)
        intent.putExtra(FourthActivity.TRANSMITTED_ID_5_to_4, arrToTrans)
        startActivity(intent)
    }

    private fun onClickSubmit(arrToTrans: IntArray?, arrStringToTrans: ArrayList<String>?) {
        val intent = Intent(this, SixActivity::class.java)
        intent.putExtra(SixActivity.TRANSMITTED_ID_5_to_6, arrToTrans)
        intent.putExtra(SixActivity.TRANSMITTED_STRING_5_to_6, arrStringToTrans)
        startActivity(intent)
    }

    companion object{
        const val TRANSMITTED_ID_4_to_5 = "transmittedIdRadioButton"
        const val TRANSMITTED_STRING_4_to_5 = "transmittedStringRadioButton"
    }



}