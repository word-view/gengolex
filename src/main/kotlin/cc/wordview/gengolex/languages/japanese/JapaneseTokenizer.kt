package cc.wordview.gengolex.languages.japanese

import cc.wordview.gengolex.languages.Verb
import java.util.regex.Pattern

object JapaneseTokenizer : IJapaneseTokenizer {
    override val kanjiDictionary: List<KanjiWord> = listOf(
        KanjiWord("走", listOf("走る", "走ってた", "走っています"))
    )
    override val hiraganaDictionary: List<Verb> = listOf()
    override val katakanaDictionary: List<Verb> = listOf()

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