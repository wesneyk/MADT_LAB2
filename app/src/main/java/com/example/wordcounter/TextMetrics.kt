package com.example.wordcounter

data class MetricsResult(
    val sentences: Int,
    val words: Int,
    val punctuation: Int,
    val numbers: Int
)

class TextMetrics {
    fun calculate(text: String): MetricsResult {
        if (text.isBlank()) {
            return MetricsResult(0, 0, 0, 0)
        }

        // 1. Calculate sentences
        // A sentence ends with . ! or ? (several in a row count as one)
        // A text without an ending sign but with words counts as 1 sentence.
        var sentences = 0
        var hasWordsForCurrentSentence = false
        var previousWasEndingMark = false

        for (i in text.indices) {
            val char = text[i]
            if (char == '.' || char == '!' || char == '?') {
                if (!previousWasEndingMark && hasWordsForCurrentSentence) {
                    sentences++
                }
                previousWasEndingMark = true
                hasWordsForCurrentSentence = false
            } else {
                previousWasEndingMark = false
                if (char.isLetterOrDigit()) {
                    hasWordsForCurrentSentence = true
                }
            }
        }
        // If the text ends without punctuation but has words
        if (hasWordsForCurrentSentence) {
            sentences++
        }

        // 2. Calculate words and numbers
        // Words are groups of letters or digits.
        // Numbers are groups of digits.
        val tokens = text.split(Regex("[^\\p{L}\\p{Nd}]+")).filter { it.isNotBlank() }
        var words = 0
        var numbers = 0

        for (token in tokens) {
            val hasLetterOrDigit = token.any { it.isLetterOrDigit() }
            if (hasLetterOrDigit) {
                words++
            }

            if (token.all { it.isDigit() }) {
                numbers++
            }
        }

        // 3. Calculate punctuation
        // Punctuation marks are any non-alphanumeric and non-whitespace characters
        var punctuation = 0
        for (char in text) {
            if (!char.isLetterOrDigit() && !char.isWhitespace()) {
                punctuation++
            }
        }

        return MetricsResult(
            sentences = sentences,
            words = words,
            punctuation = punctuation,
            numbers = numbers
        )
    }
}
