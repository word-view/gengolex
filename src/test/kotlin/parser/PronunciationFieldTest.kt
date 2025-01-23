package parser

import GengolexTest
import cc.wordview.gengolex.Language
import cc.wordview.gengolex.Parser
import kotlin.test.Test
import kotlin.test.assertEquals

class PronunciationFieldTest : GengolexTest() {
    @Test
    fun japanese() {
        testPronunciation("雨が降る", "ame", Language.JAPANESE)
    }

    @Suppress("SameParameterValue")
    private fun testPronunciation(text: String, expectedPronunciation: String, lang: Language) {
        val parser = Parser(lang, dictionaries)
        val word = parser.findWords(text).single()
        assertEquals(expectedPronunciation, word.pronunciation)
    }
}