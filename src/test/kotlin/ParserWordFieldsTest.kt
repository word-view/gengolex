import cc.wordview.gengolex.Language
import cc.wordview.gengolex.Parser
import cc.wordview.gengolex.word.DerivatableWord
import cc.wordview.gengolex.word.Representation
import cc.wordview.gengolex.languages.japanese.JapaneseKanjiStrategy
import cc.wordview.gengolex.languages.japanese.JapaneseTokenizer
import cc.wordview.gengolex.word.Type
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ParserWordFieldsTest {
    private val testResourcesPath = object {}.javaClass.classLoader.getResource("")?.toString()?.replace("file:", "")

    @Test
    fun englishWords() {
        val parser = Parser(Language.ENGLISH, "${testResourcesPath}/dictionaries/")

        testFindWords(
            parser,
            "I am running",
            "running",
            "running",
            Type.VERB,
            false,
            Representation.DESCRIPTION,
            null,
            null
        )
        testFindWords(parser, "Run", "run", "run", Type.VERB, false, Representation.DESCRIPTION, null, null, 2)
        testFindWords(parser, "I ran", "ran", "ran", Type.VERB, false, Representation.DESCRIPTION, null, null)
    }

    @Test
    fun portugueseWords() {
        val parser = Parser(Language.PORTUGUESE, "${testResourcesPath}/dictionaries/")

        testFindWords(parser, "Correr", "run", "correr", Type.VERB, false, Representation.DESCRIPTION, null, null, 3)
        testFindWords(
            parser,
            "Estou correndo",
            "running",
            "correndo",
            Type.VERB,
            false,
            Representation.DESCRIPTION,
            null,
            null
        )
        testFindWords(parser, "Corri", "ran", "corri", Type.VERB, false, Representation.DESCRIPTION, null, null)
        testFindWords(parser, "Corra", "run", "corra", Type.VERB, false, Representation.DESCRIPTION, null, null)
        testFindWords(
            parser,
            "Peguei o guarda-chuva",
            "umbrella",
            "guarda-chuva",
            Type.NOUN,
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

        testFindWords(parser, "走", "run", "走", Type.VERB, false, Representation.BOTH, null, null, 3)
        testFindWords(
            parser,
            "僕は走っています",
            "running",
            "走っています",
            Type.VERB,
            false,
            Representation.BOTH,
            null,
            null
        )
        testFindWords(parser, "僕は走ってた", "ran", "走ってた", Type.VERB, false, Representation.BOTH, null, null)
        testFindWords(parser, "僕は走る", "run", "走る", Type.VERB, false, Representation.BOTH, null, null)
        testFindWords(parser, "私は", "i", "私", Type.NOUN, false, Representation.DESCRIPTION, "Me, i", null)
        testFindWords(parser, "雨が降る", "rain", "雨", Type.NOUN, true, Representation.ILLUSTRATION, null, "ame")
    }

    private fun testFindWords(
        parser: Parser,
        text: String,
        expectedParent: String,
        expectedWord: String,
        expectedType: Type,
        expectedRepresentability: Boolean,
        expectedRepresentation: Representation,
        expectedDescription: String?,
        expectedPronunciation: String?
    ) {
        val words = parser.findWords(text)

        assertEquals(expectedParent, words.single().parent)
        assertEquals(expectedWord, words.single().word)
        assertEquals(expectedType.name, words.single().type)
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
        expectedType: Type,
        expectedRepresentability: Boolean,
        expectedRepresentation: Representation,
        expectedDescription: String?,
        expectedPronunciation: String?,
        expectedDerivationCount: Int,
    ) {
        val words = parser.findWords(text)

        assertEquals(expectedParent, words.single().parent)
        assertEquals(expectedWord, words.single().word)
        assertEquals(expectedType.name, words.single().type)
        assertEquals(expectedRepresentability, words.single().representable)
        assertEquals(expectedRepresentation.name, words.single().representation)
        assertEquals(expectedDescription, words.single().description)
        assertEquals(expectedPronunciation, words.single().pronunciation)
        assertEquals(expectedDerivationCount, (words.single() as DerivatableWord).derivations.size)
    }
}