import cc.wordview.gengolex.Language
import cc.wordview.gengolex.LanguageNotFoundException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class LanguageTest {
    @Test
    fun getEnglish() {
        assertDoesNotThrow {
            val lang = Language.byTag("en")
            assertEquals(Language.ENGLISH, lang)
            assertEquals("english", lang.dictionaryName)
        }
    }

    @Test
    fun getJapanese() {
        assertDoesNotThrow {
            val lang = Language.byTag("ja")
            assertEquals(Language.JAPANESE, lang)
            assertEquals("kanji", lang.dictionaryName)
        }
    }

    @Test
    fun getPortuguese() {
        assertDoesNotThrow {
            val lang = Language.byTag("pt")
            assertEquals(Language.PORTUGUESE, lang)
            assertEquals("portuguese", lang.dictionaryName)
        }
    }

    @Test
    fun getInexistent() {
        assertThrows<LanguageNotFoundException> {
            Language.byTag("kr")
        }
    }
}