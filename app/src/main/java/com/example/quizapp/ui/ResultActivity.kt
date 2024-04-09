package com.example.quizapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable.ConstantState
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.MainActivity
import com.example.quizapp.R
import com.example.quizapp.utils.Constants
import com.google.android.material.textfield.TextInputEditText

class ResultActivity : AppCompatActivity() {
    private lateinit var userName: TextView
    private lateinit var scoreResult: TextView
    private lateinit var buttonResult: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        userName = findViewById(R.id.user_name)
        scoreResult = findViewById(R.id.text_view_score)
        buttonResult = findViewById(R.id.button_result)

        scoreResult.text = "Your score is ${intent.getStringExtra(Constants.SCORE)} out ${intent.getStringExtra(Constants.TOTAL_QUESTIONS)}"
        userName.text = intent.getStringExtra(Constants.USER_NAME)

        buttonResult.setOnClickListener {
            Intent(this@ResultActivity, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }
}