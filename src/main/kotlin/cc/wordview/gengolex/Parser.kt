package cc.wordview.gengolex

import cc.wordview.gengolex.languages.english.EnglishTokenizer
import cc.wordview.gengolex.languages.japanese.JapaneseTokenizer
import cc.wordview.gengolex.languages.portuguese.PortugueseTokenizer
import java.util.ArrayList

fun findWords(source: String, language: Language): ArrayList<String> {
    val sourceWords = source.split(" ")

    return when (language) {
        Language.ENGLISH -> EnglishTokenizer.tokenize(sourceWords)
        Language.JAPANESE -> JapaneseTokenizer.tokenize(sourceWords)
        Language.PORTUGUESE -> PortugueseTokenizer.tokenize(sourceWords)
    }
}