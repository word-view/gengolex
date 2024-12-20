import cc.wordview.gengolex.Language
import cc.wordview.gengolex.Parser
import cc.wordview.gengolex.languages.DerivatableWord
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ParserWordFieldsTest {
    private val testResourcesPath = object {}.javaClass.classLoader.getResource("")?.toString()?.replace("file:", "")

    @Test
    fun englishWords() {
        val parser = Parser(Language.ENGLISH, "${testResourcesPath}/dictionaries/")

        testFindWords(parser, "Run", "run", "run", false, null, 2)
        testFindWords(parser, "I am running", "running", "running", false, null)
        testFindWords(parser, "I ran", "ran", "ran", false, null)
    }

    @Test
    fun portugueseWords() {
        val parser = Parser(Language.PORTUGUESE, "${testResourcesPath}/dictionaries/")

        testFindWords(parser, "Correr", "run", "correr", false, null, 3)
        testFindWords(parser, "Estou correndo", "running", "correndo", false, null)
        testFindWords(parser, "Corri", "ran", "corri", false, null)
        testFindWords(parser, "Corra", "run", "corra", false, null)
        testFindWords(parser, "Peguei o guarda-chuva", "umbrella", "guarda-chuva", true, null)
    }

    @Test
    fun japaneseWords() {
        val parser = Parser(Language.JAPANESE, "${testResourcesPath}/dictionaries/")

        testFindWords(parser, "走", "run", "走", false, null, 3)
        testFindWords(parser, "僕は走っています", "running", "走っています", false, null)
        testFindWords(parser, "僕は走ってた", "ran", "走ってた", false, null)
        testFindWords(parser, "僕は走る", "run", "走る", false, null)
        testFindWords(parser, "私は", "i", "私", false, null)
        testFindWords(parser, "雨が降る", "rain", "雨", true, "ame")
    }

    private fun testFindWords(
        parser: Parser,
        text: String,
        expectedParent: String,
        expectedWord: String,
        expectedRepresentability: Boolean,
        expectedPronunciation: String?
    ) {
        val words = parser.findWords(text)

        assertEquals(expectedParent, words.single().parent)
        assertEquals(expectedWord, words.single().word)
        assertEquals(expectedRepresentability, words.single().representable)
        assertEquals(expectedPronunciation, words.single().pronunciation)
    }

    private fun testFindWords(
        parser: Parser,
        text: String,
        expectedParent: String,
        expectedWord: String,
        expectedRepresentability: Boolean,
        expectedPronunciation: String?,
        expectedDerivationCount: Int,
    ) {
        val words = parser.findWords(text)

        assertEquals(expectedParent, words.single().parent)
        assertEquals(expectedWord, words.single().word)
        assertEquals(expectedRepresentability, words.single().representable)
        assertEquals(expectedPronunciation, words.single().pronunciation)
        assertEquals(expectedDerivationCount, (words.single() as DerivatableWord).derivations.size)
    }
}