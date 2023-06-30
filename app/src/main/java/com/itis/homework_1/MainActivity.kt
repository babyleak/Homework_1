package com.itis.homework_1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var heightEditText: EditText
    private lateinit var weightEditText: EditText
    private lateinit var resultTextView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        heightEditText = findViewById(R.id.heightEditText)
        weightEditText = findViewById(R.id.weightEditText)
        resultTextView = findViewById(R.id.resultTextView)

        val calculateButton: Button = findViewById(R.id.calculateButton)
        calculateButton.setOnClickListener {
            calculateBMI()
        }
    }

    private fun calculateBMI() {
        val height = heightEditText.text.toString().toFloatOrNull()
        val weight = weightEditText.text.toString().toFloatOrNull()

        if (height == null || weight == null) {
            resultTextView.text = "Данные введены некорректно."
        } else {
            if (height > 0 && height < 250 && weight > 0 && weight < 250) {
                val bmi = calculateBMIValue(height, weight)
                val bmiCategory = getBMICategory(bmi)
                resultTextView.text = "Индекс массы тела (ИМТ): $bmi\nКатегория: $bmiCategory"
            } else {
                resultTextView.text = "Данные введены некорректно."
            }
        }
    }

    private fun calculateBMIValue(height: Float, weight: Float): Float {
        val heightInMeters = height / 100
        return weight / (heightInMeters * heightInMeters)
    }

    private fun getBMICategory(bmi: Float): String {
        return when {
            bmi < 18.5 -> "Недостаточный вес"
            bmi < 25 -> "Нормальный вес"
            bmi < 30 -> "Избыточный вес"
            else -> "Ожирение"
        }
    }
}