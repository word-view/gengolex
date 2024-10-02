package cc.wordview.gengolex.languages.japanese

import cc.wordview.gengolex.NoDictionaryException
import cc.wordview.gengolex.languages.Tokenizer
import cc.wordview.gengolex.languages.DerivatableWord
import cc.wordview.gengolex.languages.Word
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.util.HashMap
import java.util.regex.Pattern

object JapaneseTokenizer : Tokenizer {
    var kanjiDictionary: List<DerivatableWord> = listOf()
    var hiraganaDictionary: List<DerivatableWord> = listOf()
    var katakanaDictionary: List<DerivatableWord> = listOf()

    override fun tokenize(words: List<String>): ArrayList<Word> {
        val wordsByChars = words.joinToString()
            .replace("、", "")
            .replace("。", "")
            .split("")

        val wordsFound = ArrayList<Word>()

        var charsToSkipNext = 0

        for (char in wordsByChars) {
            if (charsToSkipNext > 0) {
                charsToSkipNext--
                continue
            }

            var wordsString = wordsByChars.joinToString("")

            for (foundWord in wordsFound) {
                wordsString = wordsString.replace(foundWord.word, "")
            }

            val kanji = tokenizeKanji(
                char,
                wordsString
                    .replaceFirst(char, "TO_REPLACE")
                    .substringBefore(char)
                    .replaceFirst("TO_REPLACE", char)
            )

            if (kanji != null) {
                wordsFound.add(kanji)
                charsToSkipNext = kanji.word.count() - 1
            }
        }

        return wordsFound
    }

    override fun initializeDictionary(path: String) {
        // Only initialize kanji for now
        val kanjiDictionaryJson = File("$path/kanji.json")
            .inputStream()
            .readBytes()
            .toString(Charsets.UTF_8)

        if (kanjiDictionaryJson.isEmpty())
            throw NoDictionaryException("Unable to find a dictionary for kanji")

        val typeToken = object : TypeToken<List<DerivatableWord>>() {}.type

        val parsedDictionary = Gson().fromJson<List<DerivatableWord>>(kanjiDictionaryJson, typeToken)

        kanjiDictionary = parsedDictionary
    }

    override fun initializeDictionary(dictionaries: HashMap<String, String>) {
        val kanjiDictionary = dictionaries["kanji"]

        if (kanjiDictionary.isNullOrEmpty())
            throw NoDictionaryException("Unable to find a dictionary for kanji")

        val typeToken = object : TypeToken<List<DerivatableWord>>() {}.type

        val parsedDictionary = Gson().fromJson<List<DerivatableWord>>(kanjiDictionary, typeToken)

        this.kanjiDictionary = parsedDictionary
    }


    private fun tokenizeKanji(char: String, original: String): Word? {
        val pattern = Pattern.compile("[一-龯]")
        val isKanji = pattern.matcher(char).matches()

        if (!isKanji) return null

        var foundKanjiWord: Word? = null

        for (kanjiWord in kanjiDictionary) {
            if (kanjiWord.word == char) {
                // if no derivations are found the word is the plain kanji itself
                foundKanjiWord = kanjiWord

                // could be null if derivations is not set in the JSON, like {"parent":"walk","word":"歩く"}
                @Suppress("SENSELESS_COMPARISON")
                if (kanjiWord.derivations != null) for (derivation in kanjiWord.derivations) {
                    if (original.contains(derivation.word)) {
                        foundKanjiWord = derivation
                    }
                }
            }
        }

        return foundKanjiWord
    }
}