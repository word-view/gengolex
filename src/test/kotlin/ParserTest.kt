import cc.wordview.gengolex.Language
import cc.wordview.gengolex.NoDictionaryException
import cc.wordview.gengolex.Parser
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ParserTest {
    private val testResourcesPath = object {}.javaClass.classLoader.getResource("")?.toString()?.replace("file:", "")

    @Test
    fun findEnglishWords() {
        val parser = Parser(Language.ENGLISH, "${testResourcesPath}/dictionaries/")

        var words = parser.findWords("Run")
        assertEquals("run", words.single().word)

        words = parser.findWords("I am running")
        assertEquals("running", words.single().word)

        words = parser.findWords("I ran")
        assertEquals("ran", words.single().word)

        words = parser.findWords("I run, i ran, i am running.")
        assertEquals(3, words.size)
    }

    @Test
    fun findJapaneseWords() {
        val parser = Parser(Language.JAPANESE, "${testResourcesPath}/dictionaries/")

        var words = parser.findWords("僕は走っています")
        assertEquals("走っています", words.single().word)

        words = parser.findWords("走ってた")
        assertEquals("走ってた", words.single().word)

        words = parser.findWords("走る")
        assertEquals("走る", words.single().word)

        words = parser.findWords("僕は走っています、走ってた、走る。")
        assertEquals(3, words.size)

        words = parser.findWords("僕は走っています走ってた走る")
        assertEquals(3, words.size)

        words = parser.findWords("走る、走ってた、僕は走っています。")
        assertEquals(3, words.size)
    }

    @Test
    fun findPortugueseWords() {
        val parser = Parser(
            Language.PORTUGUESE, "${testResourcesPath}/dictionaries/"
        )

        var words = parser.findWords("Correr")
        assertEquals("correr", words.single().word)

        words = parser.findWords("Estou correndo")
        assertEquals("correndo", words.single().word)

        words = parser.findWords("Corri")
        assertEquals("corri", words.single().word)

        words = parser.findWords("Corra")
        assertEquals("corra", words.single().word)

        words = parser.findWords("Correr, correndo, corri, corra")
        assertEquals(4, words.size)
    }

    @Test
    fun findEnglishFailsWhenNoDictionary() {
        assertThrows(NoDictionaryException::class.java) {
            val parser = Parser(Language.ENGLISH)
            parser.findWords("Run")
        }
    }

    @Test
    fun findJapaneseFailsWhenNoDictionary() {
        assertThrows(NoDictionaryException::class.java) {
            val parser = Parser(Language.JAPANESE)
            parser.findWords("走る")
        }
    }

    @Test
    fun findPortugueseFailsWhenNoDictionary() {
        assertThrows(NoDictionaryException::class.java) {
            val parser = Parser(Language.PORTUGUESE)
            parser.findWords("Corra")
        }
    }

    @Test
    fun findEnglishWordsRuntimeDictionary() {
        val parser = Parser(Language.ENGLISH)
        parser.addDictionary("english", englishDictionaryJsonString)

        var words = parser.findWords("Run")
        assertEquals("run", words.single().word)

        words = parser.findWords("I am running")
        assertEquals("running", words.single().word)

        words = parser.findWords("I ran")
        assertEquals("ran", words.single().word)

        words = parser.findWords("I run, i ran, i am running.")
        assertEquals(3, words.size)
    }

    @Test
    fun findJapaneseWordsRuntimeDictionary() {
        val parser = Parser(Language.JAPANESE)
        parser.addDictionary("kanji", kanjiDictionaryJsonString)

        var words = parser.findWords("僕は走っています")
        assertEquals("走っています", words.single().word)

        words = parser.findWords("走ってた")
        assertEquals("走ってた", words.single().word)

        words = parser.findWords("走る")
        assertEquals("走る", words.single().word)

        words = parser.findWords("僕は走っています、走ってた、走る。")
        assertEquals(3, words.size)

        words = parser.findWords("僕は走っています走ってた走る")
        assertEquals(3, words.size)

        words = parser.findWords("走る、走ってた、僕は走っています。")
        assertEquals(3, words.size)
    }

    @Test
    fun findPortugueseWordsRuntimeDictionary() {
        val parser = Parser(Language.PORTUGUESE)
        parser.addDictionary("portuguese", portugueseDictionaryJsonString)

        var words = parser.findWords("Correr")
        assertEquals("correr", words.single().word)

        words = parser.findWords("Estou correndo")
        assertEquals("correndo", words.single().word)

        words = parser.findWords("Corri")
        assertEquals("corri", words.single().word)

        words = parser.findWords("Corra")
        assertEquals("corra", words.single().word)

        words = parser.findWords("Correr, correndo, corri, corra")
        assertEquals(4, words.size)
    }
}