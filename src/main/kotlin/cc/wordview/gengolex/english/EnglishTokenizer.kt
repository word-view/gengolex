package cc.wordview.gengolex.english

import java.util.ArrayList

fun englishTokenizer(words: List<String>): ArrayList<String> {
    val wordsFound = ArrayList<String>()

    for (word in words) {
        var wordClean = word.replace(",", "").replace(".", "")

        for (englishWord in dictionary) {
            if (englishWord.verb == wordClean.lowercase()) {
                wordsFound.add(wordClean)
            }

            if (wordClean.lowercase() in englishWord.derivations) {
                wordsFound.add(wordClean)
            }
        }
    }

    return wordsFound
}