package parser

import GengolexTest
import cc.wordview.gengolex.Language
import cc.wordview.gengolex.Parser
import kotlin.test.Test
import kotlin.test.assertEquals

class ParentFieldTest : GengolexTest() {
    @Test
    fun japanese() {
        testParent("走", "run", Language.JAPANESE)
        testParent("僕は走っています", "running", Language.JAPANESE)
        testParent("僕は走ってた", "ran", Language.JAPANESE)
        testParent("僕は走る", "run", Language.JAPANESE)
        testParent("私は", "i", Language.JAPANESE)
        testParent("雨が降る", "rain", Language.JAPANESE)
    }

    @Test
    fun portuguese() {
        testParent("Irei correr", "run", Language.PORTUGUESE)
        testParent("Eu corri", "ran", Language.PORTUGUESE)
        testParent("Estou correndo", "running", Language.PORTUGUESE)
        testParent("Corra", "run", Language.PORTUGUESE)
        testParent("Pegue o guarda-chuva", "umbrella", Language.PORTUGUESE)
    }

    @Test
    fun english() {
        testParent("Run fast", "run", Language.ENGLISH)
        testParent("I am running", "running", Language.ENGLISH)
        testParent("He ran", "ran", Language.ENGLISH)
    }

    private fun testParent(text: String, expectedParent: String, lang: Language) {
        val parser = Parser(lang, dictionaries)
        val word = parser.findWords(text).single()
        assertEquals(expectedParent, word.parent)
    }
}