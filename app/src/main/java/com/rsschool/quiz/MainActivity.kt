package com.rsschool.quiz

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.rsschool.quiz.databinding.ActivityQuiz1Binding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuiz1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuiz1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var arrayAnswers = IntArray(5) {0}
        val arrayStringAnswers = arrayListOf("","","","","")
        val arrFromSecActivity = intent.getIntArrayExtra(TRANSMITTED_ID_2_to_1)

        if (arrFromSecActivity != null) {
            arrayAnswers = arrFromSecActivity
            binding.radioGroup.check(arrFromSecActivity[0])
            binding.nextButton.isEnabled = true
        }

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId -> if (checkedId != 0) {
            binding.nextButton.isEnabled = true
        } }

        binding.nextButton.setOnClickListener {
            arrayAnswers[0] = binding.radioGroup.checkedRadioButtonId
            arrayStringAnswers[0] = findViewById<RadioButton>(arrayAnswers[0]).text.toString()
            onClickNext(arrayAnswers, arrayStringAnswers)
        }
    }

    private fun onClickNext(arrToTrans: IntArray, arrStringToTrans: ArrayList<String>) {
        val intent = Intent(this@MainActivity, SecActivity::class.java)
        intent.putExtra(SecActivity.TRANSMITTED_ID_1_to_2, arrToTrans)
        intent.putExtra(SecActivity.TRANSMITTED_STRING_1_to_2, arrStringToTrans)
        startActivity(intent)
    }

    companion object{
        const val TRANSMITTED_ID_2_to_1 = "transmittedIfFromNext"
    }
}
