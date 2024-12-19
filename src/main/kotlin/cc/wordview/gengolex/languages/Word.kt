package cc.wordview.gengolex.languages

/**
 * Represents any simple word in any language
 *
 * @property parent Identifier used to associate a word to a global equivalent of it (basically the translation of it in english)
 * @property word The word itself in the respective language
 * @property representable Indicates whether this word is representable by an illustration.
 */
open class Word(
    val parent: String,
    val word: String,
    val representable: Boolean = true,
)