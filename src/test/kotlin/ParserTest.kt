import cc.wordview.gengolex.Language
import cc.wordview.gengolex.findWords
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

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
        var words = findWords("僕は走っています", Language.JAPANESE)
        assertTrue(words.single() == "走っています")

        words = findWords("走ってた", Language.JAPANESE)
        assertTrue(words.single() == "走ってた")

        words = findWords("走る", Language.JAPANESE)
        assertTrue(words.single() == "走る")

        words = findWords("僕は走っています、走ってた、走る。", Language.JAPANESE)
        assertEquals(3, words.size)

        words = findWords("僕は走っています走ってた走る", Language.JAPANESE)
        assertEquals(3, words.size)

        words = findWords("走る、走ってた、僕は走っています。", Language.JAPANESE)
        assertEquals(3, words.size)
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