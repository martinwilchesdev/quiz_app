package com.example.quizapp.ui

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
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
    private val currentPosition = 1
    private var selectedOptionPosition = 0
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
        checkButton = findViewById(R.id.button_check)

        textViewQuestionOne.setOnClickListener(this)
        textViewQuestionTwo.setOnClickListener(this)
        textViewQuestionThree.setOnClickListener(this)
        textViewQuestionFour.setOnClickListener(this)

        checkButton.setOnClickListener(this)

        questionList = Constants.getQuestion()

        setQuestion()
    }

    private fun setQuestion() {
        val question = questionList[currentPosition]
        flagImage.setImageResource(question.image)
        progressBar.progress = currentPosition
        textViewProgress.text = "$currentPosition/${progressBar.max}"
        textViewQuestion.text = question.question

        textViewQuestionOne.text = question.optionOne
        textViewQuestionTwo.text = question.optionTwo
        textViewQuestionThree.text = question.optionThree
        textViewQuestionFour.text = question.optionFour

        if (currentPosition == questionList.size) {
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
    }

    private fun selectedOption(textView: TextView, selectedOptionNumber: Int) {
        resetOptions()

        selectedOptionPosition = selectedOptionNumber

        textView.setTextColor(Color.parseColor("#363A43"))
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }

    override fun onClick(v: View?) {
        println("Id: ${v?.id}")
        when(v?.id) {
            R.id.text_view_option_one -> {
                selectedOption(textViewQuestionOne, textViewQuestionOne.id)
            }
            R.id.text_view_option_two -> {
                selectedOption(textViewQuestionTwo, textViewQuestionTwo.id)
            }
            R.id.text_view_option_three -> {
                selectedOption(textViewQuestionThree, textViewQuestionThree.id)
            }
            R.id.text_view_option_four -> {
                selectedOption(textViewQuestionFour, textViewQuestionFour.id)
            }
            R.id.button_check -> {

            }
        }
    }
}