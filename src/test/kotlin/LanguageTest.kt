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
    fun getEnglish_byTag_DifferentCasing() {
        assertDoesNotThrow {
            val lang = Language.byTag("eN")
            assertEquals(Language.ENGLISH, lang)
            assertEquals("english", lang.dictionaryName)
        }
    }

    @Test
    fun getJapanese_byTag_DifferentCasing() {
        assertDoesNotThrow {
            val lang = Language.byTag("Ja")
            assertEquals(Language.JAPANESE, lang)
            assertEquals("kanji", lang.dictionaryName)
        }
    }

    @Test
    fun getPortuguese_byTag_DifferentCasing() {
        assertDoesNotThrow {
            val lang = Language.byTag("PT")
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

    @Test
    fun getEnglish_byLocaleLanguage() {
        assertDoesNotThrow {
            val lang = Language.byLocaleLanguage(Locale.ENGLISH)
            assertEquals(Language.ENGLISH, lang)
            assertEquals("english", lang.dictionaryName)
        }
    }

    @Test
    fun getJapanese_byLocaleLanguage() {
        assertDoesNotThrow {
            val lang = Language.byLocaleLanguage(Locale.JAPANESE)
            assertEquals(Language.JAPANESE, lang)
            assertEquals("kanji", lang.dictionaryName)
        }
    }

    @Test
    fun getPortuguese_byLocaleLanguage() {
        assertDoesNotThrow {
            val lang = Language.byLocaleLanguage(Locale.forLanguageTag("pt"))
            assertEquals(Language.PORTUGUESE, lang)
            assertEquals("portuguese", lang.dictionaryName)
        }
    }

    @Test
    fun getInexistent_byLocaleLanguage() {
        assertThrows<LanguageNotFoundException> {
            Language.byLocaleLanguage(Locale.forLanguageTag("fr-FR"))
        }
    }

    @Test
    fun getEnglish_byLocaleLanguage_GreatBritain() {
        assertDoesNotThrow {
            val lang = Language.byLocaleLanguage(Locale.forLanguageTag("en-GB"))
            assertEquals(Language.ENGLISH, lang)
            assertEquals("english", lang.dictionaryName)
        }
    }

    @Test
    fun getEnglish_byLocaleLanguage_UnitedStates() {
        assertDoesNotThrow {
            val lang = Language.byLocaleLanguage(Locale.forLanguageTag("en-US"))
            assertEquals(Language.ENGLISH, lang)
            assertEquals("english", lang.dictionaryName)
        }
    }

    @Test
    fun getEnglish_byLocaleLanguage_Australia() {
        assertDoesNotThrow {
            val lang = Language.byLocaleLanguage(Locale.forLanguageTag("en-AU"))
            assertEquals(Language.ENGLISH, lang)
            assertEquals("english", lang.dictionaryName)
        }
    }

    @Test
    fun getPortuguese_byLocaleLanguage_Portugal() {
        assertDoesNotThrow {
            val lang = Language.byLocaleLanguage(Locale.forLanguageTag("pt-PT"))
            assertEquals(Language.PORTUGUESE, lang)
            assertEquals("portuguese", lang.dictionaryName)
        }
    }

    @Test
    fun getPortuguese_byLocaleLanguage_Brazil() {
        assertDoesNotThrow {
            val lang = Language.byLocaleLanguage(Locale.forLanguageTag("pt-BR"))
            assertEquals(Language.PORTUGUESE, lang)
            assertEquals("portuguese", lang.dictionaryName)
        }
    }

    @Test
    fun getPortuguese_byLocaleLanguage_Angola() {
        assertDoesNotThrow {
            val lang = Language.byLocaleLanguage(Locale.forLanguageTag("pt-AO"))
            assertEquals(Language.PORTUGUESE, lang)
            assertEquals("portuguese", lang.dictionaryName)
        }
    }

    @Test
    fun getPortuguese_byLocaleLanguage_Mozambique() {
        assertDoesNotThrow {
            val lang = Language.byLocaleLanguage(Locale.forLanguageTag("pt-MZ"))
            assertEquals(Language.PORTUGUESE, lang)
            assertEquals("portuguese", lang.dictionaryName)
        }
    }
}