import cc.wordview.gengolex.Language
import cc.wordview.gengolex.Parser
import cc.wordview.gengolex.languages.DerivatableWord
import cc.wordview.gengolex.languages.Representation
import cc.wordview.gengolex.languages.japanese.JapaneseKanjiStrategy
import cc.wordview.gengolex.languages.japanese.JapaneseTokenizer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ParserWordFieldsTest {
    private val testResourcesPath = object {}.javaClass.classLoader.getResource("")?.toString()?.replace("file:", "")

    @Test
    fun englishWords() {
        val parser = Parser(Language.ENGLISH, "${testResourcesPath}/dictionaries/")

        testFindWords(parser, "I am running", "running", "running", false, Representation.DESCRIPTION, null, null)
        testFindWords(parser, "Run", "run", "run", false, Representation.DESCRIPTION, null, null, 2)
        testFindWords(parser, "I ran", "ran", "ran", false, Representation.DESCRIPTION, null, null)
    }

    @Test
    fun portugueseWords() {
        val parser = Parser(Language.PORTUGUESE, "${testResourcesPath}/dictionaries/")

        testFindWords(parser, "Correr", "run", "correr", false, Representation.DESCRIPTION, null, null, 3)
        testFindWords(parser, "Estou correndo", "running", "correndo", false, Representation.DESCRIPTION, null, null)
        testFindWords(parser, "Corri", "ran", "corri", false, Representation.DESCRIPTION, null, null)
        testFindWords(parser, "Corra", "run", "corra", false, Representation.DESCRIPTION, null, null)
        testFindWords(
            parser,
            "Peguei o guarda-chuva",
            "umbrella",
            "guarda-chuva",
            true,
            Representation.ILLUSTRATION,
            null,
            null
        )
    }

    @Test
    fun japaneseWords() {
        val parser = Parser(Language.JAPANESE, "${testResourcesPath}/dictionaries/")

        JapaneseTokenizer.kanjiStrategy = JapaneseKanjiStrategy.PREFER_DERIVATION

        testFindWords(parser, "走", "run", "走", false, Representation.BOTH, null, null, 3)
        testFindWords(
            parser,
            "僕は走っています",
            "running",
            "走っています",
            false,
            Representation.BOTH,
            null,
            null
        )
        testFindWords(parser, "僕は走ってた", "ran", "走ってた", false, Representation.BOTH, null, null)
        testFindWords(parser, "僕は走る", "run", "走る", false, Representation.BOTH, null, null)
        testFindWords(parser, "私は", "i", "私", false, Representation.DESCRIPTION, "Me, i", null)
        testFindWords(parser, "雨が降る", "rain", "雨", true, Representation.ILLUSTRATION, null, "ame")
    }

    private fun testFindWords(
        parser: Parser,
        text: String,
        expectedParent: String,
        expectedWord: String,
        expectedRepresentability: Boolean,
        expectedRepresentation: Representation,
        expectedDescription: String?,
        expectedPronunciation: String?
    ) {
        val words = parser.findWords(text)

        assertEquals(expectedParent, words.single().parent)
        assertEquals(expectedWord, words.single().word)
        assertEquals(expectedRepresentability, words.single().representable)
        assertEquals(expectedRepresentation.name, words.single().representation)
        assertEquals(expectedDescription, words.single().description)
        assertEquals(expectedPronunciation, words.single().pronunciation)
    }

    private fun testFindWords(
        parser: Parser,
        text: String,
        expectedParent: String,
        expectedWord: String,
        expectedRepresentability: Boolean,
        expectedRepresentation: Representation,
        expectedDescription: String?,
        expectedPronunciation: String?,
        expectedDerivationCount: Int,
    ) {
        val words = parser.findWords(text)

        assertEquals(expectedParent, words.single().parent)
        assertEquals(expectedWord, words.single().word)
        assertEquals(expectedRepresentability, words.single().representable)
        assertEquals(expectedRepresentation.name, words.single().representation)
        assertEquals(expectedDescription, words.single().description)
        assertEquals(expectedPronunciation, words.single().pronunciation)
        assertEquals(expectedDerivationCount, (words.single() as DerivatableWord).derivations.size)
    }
}