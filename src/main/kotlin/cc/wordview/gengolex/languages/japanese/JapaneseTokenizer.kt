package cc.wordview.gengolex.languages.japanese

import cc.wordview.gengolex.languages.Tokenizer
import cc.wordview.gengolex.languages.japanese.JapaneseKanjiStrategy.*
import cc.wordview.gengolex.word.DerivatableWord
import cc.wordview.gengolex.word.Word
import java.util.HashMap

object JapaneseTokenizer : Tokenizer {
    override var dictionary: ArrayList<DerivatableWord> = arrayListOf()
    private val wordMap: HashMap<String, DerivatableWord> = HashMap()

    @Suppress("MemberVisibilityCanBePrivate")
    var kanjiStrategy = PREFER_DERIVATION

    override fun tokenize(words: List<String>): ArrayList<Word> {
        val input = words.joinToString()
            .replace("、", "")
            .replace("。", "")

        val chars = input.toCharArray()

        val wordsFound = ArrayList<Word>()
        var i = 0

        while (i < chars.size) {
            val char = chars[i].toString()

            // tokenize hiragana first to parse kanji words that are prefixed with a kana character (e.g. お願い， お姉さん)
            val foundWord = tokenizeHiragana(char, input.substring(i)) ?:
                tokenizeKanji(char, input.substring(i))

            foundWord?.let {
                wordsFound.add(it)
                i += it.word.length
            } ?: i++
        }

        return wordsFound
    }

    private fun tokenizeHiragana(char: String, original: String): Word? {
        if (char.isEmpty() || char[0].code !in 0x3040..0x309F) return null

        wordMap[char]?.let { hiraganaWord ->
            @Suppress("UNNECESSARY_SAFE_CALL")
            val word = hiraganaWord.derivations?.find { original.startsWith(it.word) } ?: hiraganaWord

            // A single hiragana character cannot constitute a word
            return if (word.word.length == 1 && word.word == char) null
            else word
        }

        return null
    }

    private fun tokenizeKanji(char: String, original: String): Word? {
        if (char.isEmpty() || char[0].code !in 0x4E00..0x9FFF) return null

        wordMap[char]?.let { kanjiWord ->
            return when (kanjiStrategy) {
                PREFER_PARENT -> kanjiWord
                PREFER_DERIVATION -> {
                    @Suppress("UNNECESSARY_SAFE_CALL")
                    kanjiWord.derivations?.find { original.startsWith(it.word) } ?: kanjiWord
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