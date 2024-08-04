package cc.wordview.gengolex

import cc.wordview.gengolex.languages.english.EnglishTokenizer
import cc.wordview.gengolex.languages.japanese.JapaneseTokenizer
import cc.wordview.gengolex.languages.portuguese.PortugueseTokenizer
import java.util.ArrayList
import java.util.HashMap

class Parser(private var language: Language, private var dictionariesPath: String = "") {
    private val runtimeDictionaries = HashMap<String, String>()

    fun findWords(source: String): ArrayList<String> {
        val sourceWords = source.split(" ")

        return when (language) {
            Language.ENGLISH -> {
                if (dictionariesPath.isNotEmpty())
                    EnglishTokenizer.initializeDictionary(dictionariesPath)
                else
                    EnglishTokenizer.initializeDictionary(runtimeDictionaries)

                EnglishTokenizer.tokenize(sourceWords)
            }
            Language.JAPANESE -> {
                if (dictionariesPath.isNotEmpty())
                    JapaneseTokenizer.initializeDictionary(dictionariesPath)
                else
                    JapaneseTokenizer.initializeDictionary(runtimeDictionaries)

                JapaneseTokenizer.tokenize(sourceWords)
            }
            Language.PORTUGUESE -> {
                if (dictionariesPath.isNotEmpty())
                    PortugueseTokenizer.initializeDictionary(dictionariesPath)
                else
                    PortugueseTokenizer.initializeDictionary(runtimeDictionaries)

                PortugueseTokenizer.tokenize(sourceWords)
            }
        }
    }

    fun addDictionary(name: String, content: String) {
        runtimeDictionaries[name] = content;
    }
}
