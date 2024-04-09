package com.example.quizapp.utils

import com.example.quizapp.R
import com.example.quizapp.model.Question

object Constants {
    const val TOTAL_QUESTIONS = "total_questions"
    const val USER_NAME = "user_name"
    const val SCORE = "score_result"
    fun getQuestion(): MutableList<Question> {
        val questions = mutableListOf<Question>()
        val question = "What country does this flag belong?"

        val q1 = Question(
            1,
            question,
            R.drawable.uk_flag,
            "Italy",
            "Colombia",
            "United Kingdom",
            "Spain",
            3
        )
        questions.add(q1)

        val q2 = Question(
            2,
            question,
            R.drawable.argentina_flag,
            "Spain",
            "Argentina",
            "Japan",
            "Iran",
            2
        )
        questions.add(q2)

        val q3 = Question(
            3,
            question,
            R.drawable.norway_flag,
            "Mexico",
            "China",
            "United States",
            "Norway",
            4
        )
        questions.add(q3)

        val q4 = Question(
            4,
            question,
            R.drawable.bolivia_flag,
            "Armenia",
            "Bolivia",
            "Hungary",
            "Peru",
            2
        )
        questions.add(q4)

        val q5 = Question(
            5,
            question,
            R.drawable.brazil_flag,
            "Argentina",
            "Brazil",
            "Marocco",
            "South Africa",
            2
        )
        questions.add(q5)

        val q6 = Question(
            6,
            question,
            R.drawable.italy_flag,
            "Egypt",
            "Canada",
            "France",
            "Italy",
            4
        )
        questions.add(q6)

        val q7 = Question(
            7,
            question,
            R.drawable.china_flag,
            "China",
            "Colombia",
            "United Kingdom",
            "Spain",
            1
        )
        questions.add(q7)

        val q8 = Question(
            1,
            question,
            R.drawable.india_flag,
            "Pakistan",
            "South Korea",
            "India",
            "Switzerland",
            3
        )
        questions.add(q8)

        val q9 = Question(
            1,
            question,
            R.drawable.ecuador_flag,
            "Ecuador",
            "Colombia",
            "Panama",
            "Venezuela",
            1
        )
        questions.add(q9)

        val q10 = Question(
            10,
            question,
            R.drawable.germany_flag,
            "Croatia",
            "Germany",
            "Senegal",
            "Portugal",
            2
        )
        questions.add(q10)

        return questions
    }
}