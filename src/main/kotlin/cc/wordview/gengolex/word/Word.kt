package cc.wordview.gengolex.word

/**
 * Represents a word in any given language.
 *
 * @property parent Identifier used to associate a word to a global equivalent of it (basically the translation of it in english)
 * @property word The word itself in the respective language
 * @property representable Indicates whether this word is representable by an image/illustration
 * @property pronunciation How the word is spelled
 */
open class Word(
    val parent: String,
    val word: String,
    val representable: Boolean = false,
    val pronunciation: String? = null,
)