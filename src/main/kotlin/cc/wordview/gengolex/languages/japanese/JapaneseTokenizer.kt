package cc.wordview.gengolex.languages.japanese

import cc.wordview.gengolex.languages.Tokenizer
import cc.wordview.gengolex.languages.DerivatableWord
import cc.wordview.gengolex.languages.Word
import java.util.HashMap
import java.util.regex.Pattern

object JapaneseTokenizer : Tokenizer {
    override var dictionary: ArrayList<DerivatableWord> = arrayListOf()

    @Suppress("MemberVisibilityCanBePrivate")
    var kanjiStrategy = JapaneseKanjiStrategy.PREFER_DERIVATION

    private val kanjiPattern: Pattern = Pattern.compile("[一-龯]")

    override fun tokenize(words: List<String>): ArrayList<Word> {
        val wordsByChars = words.joinToString().replace("、", "").replace("。", "").split("")
        val joinedWordsString = wordsByChars.joinToString("")

        val wordsFound = ArrayList<Word>()
        var charsToSkipNext = 0

        for (i in wordsByChars.indices) {
            if (charsToSkipNext > 0) {
                charsToSkipNext--
                continue
            }

            val char = wordsByChars[i]

            val currentWordsString =
                wordsFound.fold(joinedWordsString) { acc, foundWord -> acc.replace(foundWord.word, "") }

            tokenizeKanji(char, currentWordsString)?.let {
                wordsFound.add(it)
                charsToSkipNext = it.word.length - 1
            }
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