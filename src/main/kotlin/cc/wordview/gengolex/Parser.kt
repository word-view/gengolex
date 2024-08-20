package cc.wordview.gengolex

import cc.wordview.gengolex.languages.Word
import cc.wordview.gengolex.languages.english.EnglishTokenizer
import cc.wordview.gengolex.languages.japanese.JapaneseTokenizer
import cc.wordview.gengolex.languages.portuguese.PortugueseTokenizer
import java.util.ArrayList
import java.util.HashMap

/**
 * Main class of the library
 *
 * @property language The language which this parser will use
 * @property dictionariesPath Where to lookup for dictionaries in the file system
 */
class Parser(private var language: Language, private var dictionariesPath: String = "") {
    private val runtimeDictionaries = HashMap<String, String>()

    /**
     * Look for identifiable words in the provided string
     *
     * @param source The string to lookup
     * @return A list of identified words within the given string
     */
    fun findWords(source: String): ArrayList<Word> {
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

    /**
     * Manually appends a dictionary to this parser
     *
     * @param name The name of the dictionary
     * @param content The content of the dictionary
     */
    fun addDictionary(name: String, content: String) {
        runtimeDictionaries[name] = content;
    }
}
