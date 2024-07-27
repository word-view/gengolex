package cc.wordview.gengolex.english

import java.util.ArrayList

fun englishTokenizer(words: List<String>): ArrayList<String> {
    val wordsFound = ArrayList<String>()

    for (word in words) {
        for (englishWord in dictionary) {
            if (englishWord.verb == word.lowercase()) {
                wordsFound.add(word)
            }

            if (word.lowercase() in englishWord.derivations) {
                wordsFound.add(word)
            }
        }
    }

    return wordsFound
}