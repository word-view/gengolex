package cc.wordview.gengolex.languages.japanese

import cc.wordview.gengolex.languages.Tokenizer
import cc.wordview.gengolex.languages.japanese.JapaneseKanjiStrategy.*
import cc.wordview.gengolex.word.DerivatableWord
import cc.wordview.gengolex.word.Word
import java.util.HashMap
import java.util.regex.Pattern

object JapaneseTokenizer : Tokenizer {
    override var dictionary: ArrayList<DerivatableWord> = arrayListOf()
    private val wordMap: HashMap<String, DerivatableWord> = HashMap()

    @Suppress("MemberVisibilityCanBePrivate")
    var kanjiStrategy = PREFER_DERIVATION

    private val kanjiPattern: Pattern = Pattern.compile("[一-龯]")

    override fun tokenize(words: List<String>): ArrayList<Word> {
        val input = words.joinToString()
            .replace("、", "")
            .replace("。", "")

        val chars = input.toCharArray()

        val wordsFound = ArrayList<Word>()
        var i = 0

        while (i < chars.size) {
            val char = chars[i].toString()
            tokenizeKanji(char, input.substring(i))?.let {
                wordsFound.add(it)
                i += it.word.length // Skip characters based on the word's length
            } ?: i++
        }

        return wordsFound
    }

    private fun tokenizeKanji(char: String, original: String): Word? {
        if (!kanjiPattern.matcher(char).matches()) return null

        wordMap[char]?.let { kanjiWord ->
            return when (kanjiStrategy) {
                PREFER_PARENT -> kanjiWord
                PREFER_DERIVATION -> {
                    @Suppress("UNNECESSARY_SAFE_CALL")
                    kanjiWord.derivations?.firstOrNull { original.contains(it.word) } ?: kanjiWord
                }
            }
        }

        return null
    }

    override fun initializeDictionary(path: String) {
        super.initializeDictionary(path, "kanji")
        dictionary.forEach { wordMap[it.word] = it }
    }

    override fun initializeDictionary(dictionaries: HashMap<String, String>) {
        super.initializeDictionary(dictionaries, "kanji")
        dictionary.forEach { wordMap[it.word] = it }
    }
}