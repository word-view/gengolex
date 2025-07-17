import cc.wordview.gengolex.Language
import cc.wordview.gengolex.LanguageNotFoundException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.util.Locale
import kotlin.test.assertEquals

class LanguageTest {
    @Test
    fun getEnglish_byTag() {
        assertDoesNotThrow {
            val lang = Language.byTag("en")
            assertEquals(Language.ENGLISH, lang)
            assertEquals("english", lang.dictionaryName)
        }
    }

    @Test
    fun getJapanese_byTag() {
        assertDoesNotThrow {
            val lang = Language.byTag("ja")
            assertEquals(Language.JAPANESE, lang)
            assertEquals("kanji", lang.dictionaryName)
        }
    }

    @Test
    fun getPortuguese_byTag() {
        assertDoesNotThrow {
            val lang = Language.byTag("pt")
            assertEquals(Language.PORTUGUESE, lang)
            assertEquals("portuguese", lang.dictionaryName)
        }
    }

    @Test
    fun getInexistent_byTag() {
        assertThrows<LanguageNotFoundException> {
            Language.byTag("kr")
        }
    }

    @Test
    fun getEnglish_byLocale() {
        assertDoesNotThrow {
            val lang = Language.byLocale(Locale.ENGLISH)
            assertEquals(Language.ENGLISH, lang)
            assertEquals("english", lang.dictionaryName)
        }
    }

    @Test
    fun getJapanese_byLocale() {
        assertDoesNotThrow {
            val lang = Language.byLocale(Locale.JAPANESE)
            assertEquals(Language.JAPANESE, lang)
            assertEquals("kanji", lang.dictionaryName)
        }
    }

    @Test
    fun getPortuguese_byLocale() {
        assertDoesNotThrow {
            val lang = Language.byLocale(Locale.forLanguageTag("pt"))
            assertEquals(Language.PORTUGUESE, lang)
            assertEquals("portuguese", lang.dictionaryName)
        }
    }

    @Test
    fun getInexistent_byLocale() {
        assertThrows<LanguageNotFoundException> {
            Language.byLocale(Locale.GERMAN)
        }
    }
}