package parser

import GengolexTest
import cc.wordview.gengolex.Language
import cc.wordview.gengolex.Parser
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SyntaxFieldTest : GengolexTest() {
    // Japanese
    @Test
    fun japaneseNegative() {
        val parser = Parser(Language.JAPANESE, dictionaries)

        val res = parser.findWords("変わらない").single()

        assertEquals(0, res.syntax!!.default!!.start)
        assertEquals(2, res.syntax!!.default!!.end)

        assertEquals(3, res.syntax!!.negative!!.start)
        assertEquals(4, res.syntax!!.negative!!.end)
    }

    @Test
    fun japaneseConditional() {
        val parser = Parser(Language.JAPANESE, dictionaries)

        val res = parser.findWords("近付いたら").single()

        assertEquals(0, res.syntax!!.default!!.start)
        assertEquals(2, res.syntax!!.default!!.end)

        assertEquals(3, res.syntax!!.conditional!!.start)
        assertEquals(4, res.syntax!!.conditional!!.end)
    }
}