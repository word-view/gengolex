package cc.wordview.gengolex

import cc.wordview.gengolex.languages.english.EnglishTokenizer
import cc.wordview.gengolex.languages.japanese.JapaneseTokenizer
import cc.wordview.gengolex.languages.portuguese.PortugueseTokenizer
import java.util.ArrayList

class Parser(private var language: Language, private var dictionariesPath: String) {
    fun findWords(source: String): ArrayList<String> {
        val sourceWords = source.split(" ")

        return when (language) {
            Language.ENGLISH -> {
                EnglishTokenizer.initializeDictonary(dictionariesPath)
                EnglishTokenizer.tokenize(sourceWords)
            }
            Language.JAPANESE -> {
                JapaneseTokenizer.initializeDictonary(dictionariesPath)
                JapaneseTokenizer.tokenize(sourceWords)
            }
            Language.PORTUGUESE -> {
                PortugueseTokenizer.initializeDictonary(dictionariesPath)
                PortugueseTokenizer.tokenize(sourceWords)
            }
        }
    }
}
