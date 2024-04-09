package com.example.quizapp.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.R
import com.example.quizapp.model.Question
import com.example.quizapp.utils.Constants

class QuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var progressBar: ProgressBar
    private lateinit var textViewProgress: TextView
    private lateinit var textViewQuestion: TextView
    private lateinit var flagImage: ImageView

    private lateinit var textViewQuestionOne: TextView
    private lateinit var textViewQuestionTwo: TextView
    private lateinit var textViewQuestionThree: TextView
    private lateinit var textViewQuestionFour: TextView
    private lateinit var checkButton: Button

    private lateinit var questionList: MutableList<Question>
    private lateinit var currentQuestion: Question
    private lateinit var correctAnswer: TextView
    private lateinit var userName: String

    private var optionsList = mutableListOf<TextView>()
    private var counterQuestions = 1
    private var currentOptionId = 0
    private var scoreResult = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_questions)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textViewQuestion = findViewById(R.id.question_text_view)
        textViewProgress = findViewById(R.id.text_view_progress)
        progressBar = findViewById(R.id.progress_bar)
        flagImage = findViewById(R.id.image_flag)

        textViewQuestionOne = findViewById(R.id.text_view_option_one)
        textViewQuestionTwo = findViewById(R.id.text_view_option_two)
        textViewQuestionThree = findViewById(R.id.text_view_option_three)
        textViewQuestionFour = findViewById(R.id.text_view_option_four)
        checkButton = findViewById(R.id.button_check)

        textViewQuestionOne.setOnClickListener(this)
        textViewQuestionTwo.setOnClickListener(this)
        textViewQuestionThree.setOnClickListener(this)
        textViewQuestionFour.setOnClickListener(this)

        checkButton.setOnClickListener(this)

        questionList = Constants.getQuestion()

        showNextQuestion()

        if (intent.hasExtra(Constants.USER_NAME)) {
            userName = intent.getStringExtra(Constants.USER_NAME)!!
        }
    }

    private fun showNextQuestion() {
        resetOptions()

        currentQuestion = questionList[counterQuestions - 1]

        textViewProgress.text = "$counterQuestions/${progressBar.max}"
        textViewQuestion.text = currentQuestion.question
        flagImage.setImageResource(currentQuestion.image)
        progressBar.progress = counterQuestions

        textViewQuestionOne.text = currentQuestion.optionOne
        textViewQuestionTwo.text = currentQuestion.optionTwo
        textViewQuestionThree.text = currentQuestion.optionThree
        textViewQuestionFour.text = currentQuestion.optionFour

        optionsList.add(textViewQuestionOne)
        optionsList.add(textViewQuestionTwo)
        optionsList.add(textViewQuestionThree)
        optionsList.add(textViewQuestionFour)

        correctAnswer = optionsList[currentQuestion.correctAnswer - 1]

        if (counterQuestions == questionList.size) {
            checkButton.text = "FINISH"
        } else {
            checkButton.text = "CHECK"
        }
    }

    private fun resetOptions() {
        val options = mutableListOf<TextView>()

        options.add(textViewQuestionOne)
        options.add(textViewQuestionTwo)
        options.add(textViewQuestionThree)
        options.add(textViewQuestionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }

        currentOptionId = 0
    }

    private fun selectedOption(textView: TextView) {
        resetOptions()

        currentOptionId = textView.id

        textView.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
        textView.setTextColor(Color.parseColor("#363A43"))
        textView.setTypeface(textView.typeface, Typeface.BOLD)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            textViewQuestionOne.id -> {
                selectedOption(textViewQuestionOne)
            }

            textViewQuestionTwo.id -> {
                selectedOption(textViewQuestionTwo)
            }

            textViewQuestionThree.id -> {
                selectedOption(textViewQuestionThree)
            }

            textViewQuestionFour.id -> {
                selectedOption(textViewQuestionFour)
            }

            checkButton.id -> {
                if (currentOptionId == 0) {
                    Toast.makeText(
                        this@QuestionsActivity,
                        "Select a valid option",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (checkButton.text == "FINISH") {
                    Intent(this@QuestionsActivity, ResultActivity::class.java).also {
                        it.putExtra(Constants.TOTAL_QUESTIONS, counterQuestions.toString())
                        it.putExtra(Constants.SCORE, scoreResult.toString())
                        it.putExtra(Constants.USER_NAME, userName)
                        startActivity(it)
                        finish()
                    }
                } else if (checkButton.text == "NEXT") {
                    counterQuestions++
                    showNextQuestion()
                } else {
                    checkAnswer()
                }
            }
        }
    }

    private fun checkAnswer() {
        if (correctAnswer.id == currentOptionId) {
            scoreResult++
            highlightAnswer(R.drawable.correct_option_border_bg)
        } else {
            highlightAnswer(R.drawable.wrong_option_border_bg)

            correctAnswer.background = getDrawable(R.drawable.correct_option_border_bg)
        }

        checkButton.text = "NEXT"
    }

    private fun highlightAnswer(answer: Int) {
        when (currentOptionId) {
            textViewQuestionOne.id -> {
                textViewQuestionOne.background =
                    getDrawable(answer)
            }

            textViewQuestionTwo.id -> {
                textViewQuestionTwo.background =
                    getDrawable(answer)
            }

            textViewQuestionThree.id -> {
                textViewQuestionThree.background =
                    getDrawable(answer)
            }

            textViewQuestionFour.id -> {
                textViewQuestionFour.background =
                    getDrawable(answer)
            }
        }
    }
}