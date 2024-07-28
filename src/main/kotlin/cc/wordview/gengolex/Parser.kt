package cc.wordview.gengolex

import cc.wordview.gengolex.english.englishTokenizer
import cc.wordview.gengolex.japanese.japaneseTokenizer
import cc.wordview.gengolex.portuguese.portugueseTokenizer
import java.util.ArrayList

fun findWords(source: String, language: Language): ArrayList<String> {
    val sourceWords = source.split(" ")

    return when (language) {
        Language.ENGLISH -> englishTokenizer(sourceWords)
        Language.JAPANESE -> japaneseTokenizer(sourceWords)
        Language.PORTUGUESE -> portugueseTokenizer(sourceWords)
    }
}