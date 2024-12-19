package cc.wordview.gengolex.languages

/**
 * Represents any simple word in any language
 *
 * @property parent Identifier used to associate a word to a global equivalent of it (basically the translation of it in english)
 * @property word The word itself in the respective language
 * @property representable Indicates whether this word is representable by an illustration.
 * @property pronunciation How the word should be spelled. Sometimes a word like '心' needs to be spelled as 'kokoro' instead of 'shin' causing some problems when using TTS.
 */
open class Word(
    val parent: String,
    val word: String,
    val representable: Boolean = true,
    val pronunciation: String? = null,
)