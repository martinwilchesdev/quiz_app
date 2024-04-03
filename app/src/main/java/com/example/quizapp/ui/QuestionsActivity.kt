package com.example.quizapp.ui

import android.os.Bundle
import android.provider.ContactsContract.Contacts
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.R
import com.example.quizapp.model.Question
import com.example.quizapp.utils.Constants

class QuestionsActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private lateinit var textViewProgress: TextView
    private lateinit var textViewQuestion: TextView
    private lateinit var flagImage: ImageView

    private lateinit var textViewQuestionOne: TextView
    private lateinit var textViewQuestionTwo: TextView
    private lateinit var textViewQuestionThree: TextView
    private lateinit var textViewQuestionFour: TextView

    private lateinit var questionList: MutableList<Question>
    private val currentPosition = 1
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_questions)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        progressBar = findViewById(R.id.progress_bar)
        textViewProgress = findViewById(R.id.text_view_progress)
        textViewQuestion = findViewById(R.id.question_text_view)
        flagImage = findViewById(R.id.image_flag)

        textViewQuestionOne = findViewById(R.id.text_view_option_one)
        textViewQuestionTwo = findViewById(R.id.text_view_option_two)
        textViewQuestionThree = findViewById(R.id.text_view_option_three)
        textViewQuestionFour = findViewById(R.id.text_view_option_four)
    }

    private fun setQuestion() {
        val question = questionList[currentPosition - 1]
        flagImage.setImageResource(question.image)
        progressBar.progress = currentPosition
        textViewProgress.text = "$currentPosition/${progressBar.max}"
        textViewQuestion.text = question.question

        textViewQuestionOne.text = question.optionOne
        textViewQuestionTwo.text = question.optionTwo
        textViewQuestionThree.text = question.optionThree
        textViewQuestionFour.text = question.optionFour
    }
}