package com.example.wordcounter

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val textMetrics = TextMetrics()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val mainView = findViewById<android.view.View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val inputText = findViewById<EditText>(R.id.inputText)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val btnGenerate = findViewById<Button>(R.id.btnGenerate)
        val tvHistory = findViewById<TextView>(R.id.tvHistory)

        btnCalculate.setOnClickListener {
            val text = inputText.text.toString()
            if (text.isBlank()) {
                // Show error message if empty
                Toast.makeText(this, getString(R.string.error_empty_input), Toast.LENGTH_SHORT).show()
            } else {
                // Calculate metrics
                val metrics = textMetrics.calculate(text)
                
                // Format history string
                val historyEntry = getString(
                    R.string.history_format,
                    text,
                    metrics.sentences,
                    metrics.words,
                    metrics.punctuation,
                    metrics.numbers
                )

                // Append to history view
                if (tvHistory.text.isEmpty()) {
                    tvHistory.text = historyEntry
                } else {
                    tvHistory.append("\n$historyEntry")
                }
            }
        }

        btnGenerate.setOnClickListener {
            // Get random sentence from resources
            val sentences = resources.getStringArray(R.array.test_sentences)
            val randomSentence = sentences[Random.nextInt(sentences.size)]
            inputText.setText(randomSentence)
        }
    }
}