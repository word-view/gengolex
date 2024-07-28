import cc.wordview.gengolex.Language
import cc.wordview.gengolex.Parser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ParserTest {
    @Test
    fun findEnglishWords() {
        val parser = Parser(Language.ENGLISH)

        var words = parser.findWords("Run")
        assertTrue(words.single() == "Run")

        words = parser.findWords("I am running")
        assertTrue(words.single() == "running")

        words = parser.findWords("I ran")
        assertTrue(words.single() == "ran")

        words = parser.findWords("I run, i ran, i am running.")
        assertEquals(3, words.size)
    }

    @Test
    fun findJapaneseWords() {
        val parser = Parser(Language.JAPANESE)

        var words = parser.findWords("僕は走っています")
        assertTrue(words.single() == "走っています")

        words = parser.findWords("走ってた")
        assertTrue(words.single() == "走ってた")

        words = parser.findWords("走る")
        assertTrue(words.single() == "走る")

        words = parser.findWords("僕は走っています、走ってた、走る。")
        assertEquals(3, words.size)

        words = parser.findWords("僕は走っています走ってた走る")
        assertEquals(3, words.size)

        words = parser.findWords("走る、走ってた、僕は走っています。")
        assertEquals(3, words.size)
    }

    @Test
    fun findPortugueseWords() {
        val parser = Parser(Language.PORTUGUESE)

        var words = parser.findWords("Correr")
        assertTrue(words.single() == "Correr")

        words = parser.findWords("Estou correndo")
        assertTrue(words.single() == "correndo")

        words = parser.findWords("Corri")
        assertTrue(words.single() == "Corri")

        words = parser.findWords("Corra")
        assertTrue(words.single() == "Corra")

        words = parser.findWords("Correr, correndo, corri, corra")
        assertEquals(4, words.size)
    }
}