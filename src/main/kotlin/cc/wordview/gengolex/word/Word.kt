package cc.wordview.gengolex.word

/**
 * Represents a word in any given language.
 *
 * @property parent Identifier used to associate a word to a global equivalent of it (basically the translation of it in english)
 * @property word The word itself in the respective language
 * @property type Which kind of word is (e.g. Verb, Noun)
 * @property time Which time the verb is conjugated (null if not a verb)
 * @property representable Indicates whether this word is representable by an image/illustration
 * @property representation How the image should be portrayed, as an illustration or as a simple description of it's meaning
 * @property description Describes the meaning of the word
 * @property pronunciation How the word is spelled
 */
open class Word(
    val parent: String,
    val word: String,
    val syntax: Syntax? = null,
    val type: String = Type.NOUN.name,
    val time: String? = null,
    val representable: Boolean = false,
    val representation: String = Representation.DESCRIPTION.name,
    val description: String? = null,
    val pronunciation: String? = null,
)