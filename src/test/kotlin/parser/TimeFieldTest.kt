package parser

import GengolexTest
import cc.wordview.gengolex.Language
import cc.wordview.gengolex.Parser
import cc.wordview.gengolex.word.Time
import kotlin.test.Test
import kotlin.test.assertEquals

class TimeFieldTest : GengolexTest() {
    @Test
    fun japanese() {
        testTime("走", Time.PRESENT, Language.JAPANESE)
        testTime("僕は走っています", Time.PRESENT, Language.JAPANESE)
        testTime("僕は走ってた", Time.PAST, Language.JAPANESE)
        testTime("僕は走る", Time.PRESENT, Language.JAPANESE)
        testTime("私は", null, Language.JAPANESE)
        testTime("雨が降る", null, Language.JAPANESE)
    }

    @Test
    fun portuguese() {
        testTime("Irei correr", Time.PRESENT, Language.PORTUGUESE)
        testTime("Eu corri", Time.PAST, Language.PORTUGUESE)
        testTime("Estou correndo", Time.PRESENT, Language.PORTUGUESE)
        testTime("Corra", Time.PRESENT, Language.PORTUGUESE)
        testTime("Pegue o guarda-chuva", null, Language.PORTUGUESE)
    }

    @Test
    fun english() {
        testTime("Run fast", Time.PRESENT, Language.ENGLISH)
        testTime("I am running", Time.PRESENT, Language.ENGLISH)
        testTime("He ran", Time.PAST, Language.ENGLISH)
    }


    private fun testTime(text: String, expectedType: Time?, lang: Language) {
        val parser = Parser(lang, dictionaries)
        val word = parser.findWords(text).single()
        assertEquals(expectedType?.name, word.time)
    }
}