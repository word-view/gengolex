package cc.wordview.gengolex

import cc.wordview.gengolex.english.englishTokenizer
import cc.wordview.gengolex.portuguese.portugueseTokenizer
import java.util.ArrayList

fun findWords(source: String, language: Language): ArrayList<String> {
    val sourceWords = source.split(" ")

    when (language) {
        Language.ENGLISH -> return englishTokenizer(sourceWords)
        Language.JAPANESE -> TODO()
        Language.PORTUGUESE -> return portugueseTokenizer(sourceWords)
    }
}