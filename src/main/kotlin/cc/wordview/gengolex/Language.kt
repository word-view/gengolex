package cc.wordview.gengolex

import cc.wordview.gengolex.languages.Tokenizer
import cc.wordview.gengolex.languages.english.EnglishTokenizer
import cc.wordview.gengolex.languages.japanese.JapaneseTokenizer
import cc.wordview.gengolex.languages.portuguese.PortugueseTokenizer
import java.util.Locale

/**
 * All languages that are supported by the library
 *
 */
enum class Language(val tag: String, val dictionaryName: String, val tokenizer: Tokenizer, @Suppress("unused") val locale: Locale) {
    ENGLISH("en", "english", EnglishTokenizer, Locale.ENGLISH),
    JAPANESE("ja", "kanji", JapaneseTokenizer, Locale.JAPANESE),
    // for some reason the portuguese locale is there but not as an enum value
    PORTUGUESE("pt", "portuguese", PortugueseTokenizer, Locale.forLanguageTag("pt"));

    companion object {

        /**
         * Returns the [Language] corresponding to the given language tag.
         *
         * @param tag The language tag (e.g., `"en"`, `"ja"`, `"pt"`).
         * @return The [Language] instance matching the tag.
         * @throws LanguageNotFoundException if no language matches the given tag.
         */
        @Throws(LanguageNotFoundException::class)
        fun byTag(tag: String): Language {
            for (lang in Language.entries) {
                if (lang.tag == tag) return lang
            }

            throw LanguageNotFoundException("Unable to find a language for the tag: $tag")
        }

        /**
         * Returns the [Language] corresponding to the given [Locale].
         *
         * This matches by exact [Locale] instance.
         *
         * @param locale The [Locale] to match.
         * @return The [Language] instance matching the locale.
         * @throws LanguageNotFoundException if no language matches the given locale.
         */
        @Throws(LanguageNotFoundException::class)
        fun byLocale(locale: Locale): Language {
            for (lang in Language.entries) {
                if (lang.locale == locale) return lang
            }

            throw LanguageNotFoundException("Unable to find a language for the locale: ${locale.displayName}")
        }

        /**
         * Returns the [Language] corresponding to the language code of the given [Locale].
         *
         * This matches only the language component the locale.
         *
         * @param locale The [Locale] whose language code will be matched.
         * @return The [Language] instance matching the language code.
         * @throws LanguageNotFoundException if no language matches the locale's language code.
         */
        @Throws(LanguageNotFoundException::class)
        fun byLocaleLanguage(locale: Locale): Language {
            val localeLanguage = locale.language

            for (lang in Language.entries) {
                if (lang.locale.language.equals(localeLanguage))
                    return lang
            }

            throw LanguageNotFoundException("Unable to find a language for the locale: ${locale.displayName}")
        }
    }
}