package parser

import GengolexTest
import cc.wordview.gengolex.Language
import cc.wordview.gengolex.Parser
import kotlin.test.Test
import kotlin.test.assertEquals

class RepresentableFieldTest : GengolexTest() {
    @Test
    fun japanese() {
        testRepresentability("走", false, Language.JAPANESE)
        testRepresentability("僕は走っています", false, Language.JAPANESE)
        testRepresentability("僕は走ってた", false, Language.JAPANESE)
        testRepresentability("僕は走る", false, Language.JAPANESE)
        testRepresentability("私は", false, Language.JAPANESE)
        testRepresentability("雨が降る", true, Language.JAPANESE)
        testRepresentability("変わらないよ", true, Language.JAPANESE)
        testRepresentability("近付いたら壊れてしまいそう", true, Language.JAPANESE)
    }

    @Test
    fun portuguese() {
        testRepresentability("Irei correr", false, Language.PORTUGUESE)
        testRepresentability("Eu corri", false, Language.PORTUGUESE)
        testRepresentability("Estou correndo", false, Language.PORTUGUESE)
        testRepresentability("Corra", false, Language.PORTUGUESE)
        testRepresentability("Pegue o guarda-chuva", true, Language.PORTUGUESE)
    }

    @Test
    fun english() {
        testRepresentability("Run fast", false, Language.ENGLISH)
        testRepresentability("I am running", false, Language.ENGLISH)
        testRepresentability("He ran", false, Language.ENGLISH)
    }


    private fun testRepresentability(text: String, expectedRepresentability: Boolean, lang: Language) {
        val parser = Parser(lang, dictionaries)
        val word = parser.findWords(text).single()
        assertEquals(expectedRepresentability, word.representable)
    }
}