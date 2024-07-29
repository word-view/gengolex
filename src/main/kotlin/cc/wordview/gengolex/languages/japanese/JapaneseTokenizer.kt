package cc.wordview.gengolex.languages.japanese

import cc.wordview.gengolex.languages.Tokenizer
import cc.wordview.gengolex.languages.Verb
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.util.regex.Pattern

object JapaneseTokenizer : Tokenizer {
    var kanjiDictionary: List<KanjiWord> = listOf()
    var hiraganaDictionary: List<Verb> = listOf()
    var katakanaDictionary: List<Verb> = listOf()

    override fun tokenize(words: List<String>): ArrayList<String> {
        val wordsByChars = words.joinToString()
            .replace("、", "")
            .replace("。", "")
            .split("")

        val wordsFound = ArrayList<String>()

        var charsToSkipNext = 0

        for (char in wordsByChars) {
            if (charsToSkipNext > 0) {
                charsToSkipNext--
                continue
            }

            var wordsString = wordsByChars.joinToString("")

            for (foundWord in wordsFound) {
                wordsString = wordsString.replace(foundWord, "")
            }

            val kanji = tokenizeKanji(
                char,
                wordsString
                    .replaceFirst(char, "TO_REPLACE")
                    .substringBefore(char)
                    .replaceFirst("TO_REPLACE", char)
            )

            if (kanji.isNotBlank()) {
                wordsFound.add(kanji)
                charsToSkipNext = kanji.count() - 1
            }
        }

        return wordsFound
    }

    override fun initializeDictonary(path: String) {
        // Only initialize kanji for now
        val kanjiDictionaryJson = File("$path/kanji.json")
            .inputStream()
            .readBytes()
            .toString(Charsets.UTF_8)

        val typeToken = object : TypeToken<List<KanjiWord>>() {}.type

        val parsedDictionary = Gson().fromJson<List<KanjiWord>>(kanjiDictionaryJson, typeToken)

        kanjiDictionary = parsedDictionary
    }


    private fun tokenizeKanji(char: String, original: String): String {
        val pattern = Pattern.compile("[一-龯]")
        val isKanji = pattern.matcher(char).matches()

        if (!isKanji) return ""

        var foundKanjiWord = ""


        for (kanjiWord in kanjiDictionary) {
            if (kanjiWord.kanji == char) {
                // if no derivations are found the word is the plain kanji itself
                foundKanjiWord = kanjiWord.kanji

                for (derivation in kanjiWord.derivations) {
                    if (original.contains(derivation)) {
                        foundKanjiWord = derivation
                    }
                }
            }
        }

        return foundKanjiWord
    }
}