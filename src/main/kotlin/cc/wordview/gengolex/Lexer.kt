package cc.wordview.gengolex

import cc.wordview.gengolex.english.englishTokenizer
import java.util.ArrayList

fun lexer(source: String, language: Language): ArrayList<String> {
    val sourceWords = source.split(" ")

    when (language) {
        Language.ENGLISH -> return englishTokenizer(sourceWords)
        Language.JAPANESE -> TODO()
        Language.PORTUGUESE -> TODO()
    }
}