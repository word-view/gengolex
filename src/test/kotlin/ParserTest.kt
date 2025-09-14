import cc.wordview.gengolex.Language
import cc.wordview.gengolex.NoDictionaryException
import cc.wordview.gengolex.Parser
import cc.wordview.gengolex.languages.japanese.JapaneseKanjiStrategy
import cc.wordview.gengolex.languages.japanese.JapaneseTokenizer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ParserTest : GengolexTest() {
    @Test
    fun findEnglishWords() {
        val parser = Parser(Language.ENGLISH, dictionaries)

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
        val parser = Parser(Language.JAPANESE, dictionaries)

        var words = parser.findWords("僕は走っています")
        assertEquals("走", words.single().word)

        words = parser.findWords("走ってた")
        assertEquals("走", words.single().word)

        words = parser.findWords("走る")
        assertEquals("走", words.single().word)

        words = parser.findWords("僕は走っています、走ってた、走る。")
        assertEquals(3, words.size)

        words = parser.findWords("僕は走っています走ってた走る")
        assertEquals(3, words.size)

        words = parser.findWords("私は走っています走ってた走る")
        assertEquals(4, words.size)

        words = parser.findWords("走る、走ってた、僕は走っています。")
        assertEquals(3, words.size)

        words = parser.findWords("雨が降る")
        assertEquals(1, words.size)

        words = parser.findWords("お誕生日おめでとう")
        assertEquals(1, words.size)

        words = parser.findWords("早く行けお願い")
        assertEquals(1, words.size)
    }

    @Test
    fun findPortugueseWords() {
        val parser = Parser(
            Language.PORTUGUESE, dictionaries
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

        words = parser.findWords("Peguei o guarda-chuva")
        assertEquals(1, words.size)
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
        assertEquals("走", words.single().word)

        words = parser.findWords("走ってた")
        assertEquals("走", words.single().word)

        words = parser.findWords("走る")
        assertEquals("走", words.single().word)

        words = parser.findWords("僕は走っています、走ってた、走る。")
        assertEquals(3, words.size)

        words = parser.findWords("僕は走っています走ってた走る")
        assertEquals(3, words.size)

        words = parser.findWords("私は走っています走ってた走る")
        assertEquals(4, words.size)

        words = parser.findWords("走る、走ってた、僕は走っています。")
        assertEquals(3, words.size)

        // hiragana
        words = parser.findWords("お誕生日おめでとう")
        assertEquals(1, words.size)

        words = parser.findWords("早く行けお願い")
        assertEquals(1, words.size)

        // katakana
        words = parser.findWords("アジトに行こうぜ")
        assertEquals(1, words.size)

        words = parser.findWords("俺のパソコンが遅い")
        assertEquals(1, words.size)
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

    @Test
    fun japaneseTokenizerPrefersParent() {
        JapaneseTokenizer.kanjiStrategy = JapaneseKanjiStrategy.PREFER_PARENT

        // 世界
        testJapanesePreferParent("こんな小さな世界で今日も", "世", "[{\"parent\":\"\",\"word\":\"世\",\"derivations\":[{\"parent\":\"world\",\"word\":\"世界\",\"representable\":true}]}]")
        testJapanesePreferParent("音で世界があふれた", "世","[{\"parent\":\"\",\"word\":\"世\",\"derivations\":[{\"parent\":\"world\",\"word\":\"世界\",\"representable\":true}]}]")
        testJapanesePreferParent("世界がなくなっても", "世","[{\"parent\":\"\",\"word\":\"世\",\"derivations\":[{\"parent\":\"world\",\"word\":\"世界\",\"representable\":true}]}]")
        testJapanesePreferParent("この世界でひとときの", "世","[{\"parent\":\"\",\"word\":\"世\",\"derivations\":[{\"parent\":\"world\",\"word\":\"世界\",\"representable\":true}]}]")

        // 太陽
        testJapanesePreferParent("太陽たいようは嫌きらい、何なにも知しらずに", "太", "[{\"parent\":\"\",\"word\":\"太\",\"derivations\":[{\"parent\":\"sun\",\"word\":\"太陽\",\"representable\":true}]}]")
    }

    private fun testJapanesePreferParent(text: String, expectedParent: String, dictionary: String) {
        val parser = Parser(Language.JAPANESE)
        parser.addDictionary("kanji", dictionary)

        val words = parser.findWords(text)

        assertEquals(1, words.size)
        assertEquals(expectedParent, words.single().word)
    }
}