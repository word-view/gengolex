import cc.wordview.gengolex.Language
import cc.wordview.gengolex.lexer
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class LexerTest {
    @Test
    fun lexAnalyzeWordEnglish() {
        // "Run", "I am running", "I ran"
        var words = lexer("Run", Language.ENGLISH)
        assertTrue(words.single() == "Run")

        words = lexer("I am running", Language.ENGLISH)
        assertTrue(words.single() == "running")

        words = lexer("I ran", Language.ENGLISH)
        assertTrue(words.single() == "ran")
    }

    @Test
    fun lexAnalyzeWordJapanese() {
        // "僕は走っています", "走ってた", "走る"
    }

    @Test
    fun lexAnalyzeWordPortuguese() {
        // "Estou correndo", "Corri", "Corra"
    }
}