package cc.wordview.gengolex.languages.japanese

import cc.wordview.gengolex.languages.Tokenizer
import cc.wordview.gengolex.word.DerivatableWord
import cc.wordview.gengolex.word.Word
import java.util.HashMap
import java.util.regex.Pattern

object JapaneseTokenizer : Tokenizer {
    override var dictionary: ArrayList<DerivatableWord> = arrayListOf()

    @Suppress("MemberVisibilityCanBePrivate")
    var kanjiStrategy = JapaneseKanjiStrategy.PREFER_DERIVATION

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

        dictionary.firstOrNull { it.word == char }?.let { kanjiWord ->
            return when (kanjiStrategy) {
                // TODO: Properly address this by removing all derivations that is not present in the phrase.
                JapaneseKanjiStrategy.PREFER_PARENT -> kanjiWord
                JapaneseKanjiStrategy.PREFER_DERIVATION -> {
                    @Suppress("UNNECESSARY_SAFE_CALL")
                    kanjiWord.derivations?.firstOrNull { original.contains(it.word) } ?: kanjiWord
                }
            }
        }

        return null
    }

    override fun initializeDictionary(path: String) {
        super.initializeDictionary(path, "kanji")
    }

    override fun initializeDictionary(dictionaries: HashMap<String, String>) {
        super.initializeDictionary(dictionaries, "kanji")
    }
}