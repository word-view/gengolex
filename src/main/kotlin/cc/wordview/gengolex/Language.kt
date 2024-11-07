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
        fun byTag(tag: String): Language {
            for (lang in Language.entries) {
                if (lang.tag == tag) return lang
            }

            throw LanguageNotFoundException("Unable to find a language for the tag: $tag")
        }
    }
}