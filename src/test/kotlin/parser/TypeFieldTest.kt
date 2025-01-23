package parser

import GengolexTest
import cc.wordview.gengolex.Language
import cc.wordview.gengolex.Parser
import cc.wordview.gengolex.word.Type
import kotlin.test.Test
import kotlin.test.assertEquals

class TypeFieldTest : GengolexTest() {
    @Test
    fun japanese() {
        testType("走", Type.VERB, Language.JAPANESE)
        testType("僕は走っています", Type.VERB, Language.JAPANESE)
        testType("僕は走ってた", Type.VERB, Language.JAPANESE)
        testType("僕は走る", Type.VERB, Language.JAPANESE)
        testType("私は", Type.NOUN, Language.JAPANESE)
        testType("雨が降る", Type.NOUN, Language.JAPANESE)
    }

    @Test
    fun portuguese() {
        testType("Irei correr", Type.VERB, Language.PORTUGUESE)
        testType("Eu corri", Type.VERB, Language.PORTUGUESE)
        testType("Estou correndo", Type.VERB, Language.PORTUGUESE)
        testType("Corra", Type.VERB, Language.PORTUGUESE)
        testType("Pegue o guarda-chuva", Type.NOUN, Language.PORTUGUESE)
    }

    @Test
    fun english() {
        testType("Run fast", Type.VERB, Language.ENGLISH)
        testType("I am running", Type.VERB, Language.ENGLISH)
        testType("He ran", Type.VERB, Language.ENGLISH)
    }
    
    private fun testType(text: String, expectedType: Type, lang: Language) {
        val parser = Parser(lang, dictionaries)
        val word = parser.findWords(text).single()
        assertEquals(expectedType.name, word.type)
    }
}