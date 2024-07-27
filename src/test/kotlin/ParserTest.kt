import cc.wordview.gengolex.Language
import cc.wordview.gengolex.findWords
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class ParserTest {
    @Test
    fun findEnglishWords() {
        var words = findWords("Run", Language.ENGLISH)
        assertTrue(words.single() == "Run")

        words = findWords("I am running", Language.ENGLISH)
        assertTrue(words.single() == "running")

        words = findWords("I ran", Language.ENGLISH)
        assertTrue(words.single() == "ran")

        words = findWords("I run, i ran, i am running.", Language.ENGLISH)
        assertEquals(3, words.size)
    }

    @Test
    fun findJapaneseWords() {
        // "僕は走っています", "走ってた", "走る"
    }

    @Test
    fun findPortugueseWords() {
        // "Estou correndo", "Corri", "Corra"
        var words = findWords("Correr", Language.PORTUGUESE)
        assertTrue(words.single() == "Correr")

        words = findWords("Estou correndo", Language.PORTUGUESE)
        assertTrue(words.single() == "correndo")

        words = findWords("Corri", Language.PORTUGUESE)
        assertTrue(words.single() == "Corri")

        words = findWords("Corra", Language.PORTUGUESE)
        assertTrue(words.single() == "Corra")

        words = findWords("Correr, correndo, corri, corra", Language.PORTUGUESE)
        assertEquals(4, words.size)
    }
}