package parser

import GengolexTest
import cc.wordview.gengolex.Language
import cc.wordview.gengolex.Parser
import kotlin.test.Test
import kotlin.test.assertEquals

class TranslationParentFieldTest : GengolexTest() {
    @Test
    fun japanese() {
        val parser = Parser(Language.JAPANESE, dictionaries)
        val word = parser.findWords("悲しみどうでもいい").single()
        assertEquals("sadness", word.translation_parent)
    }

    @Test
    fun japanese_noTranslationParent() {
        val parser = Parser(Language.JAPANESE, dictionaries)
        val word = parser.findWords("雨が降る").single()
        assertEquals(null, word.translation_parent)
    }
}