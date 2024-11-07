package cc.wordview.gengolex

import cc.wordview.gengolex.languages.Word
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

        if (dictionariesPath.isNotEmpty()) {
            language.tokenizer.initializeDictionary(dictionariesPath)
        } else {
            language.tokenizer.initializeDictionary(runtimeDictionaries)
        }

        return language.tokenizer.tokenize(sourceWords)
    }

    /**
     * Manually appends a dictionary to this parser
     *
     * @param name The name of the dictionary
     * @param content The content of the dictionary
     */
    fun addDictionary(name: String, content: String) {
        runtimeDictionaries[name] = content
    }
}
