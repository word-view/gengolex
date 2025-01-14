package cc.wordview.gengolex.word

/**
 * Represents any word that can have derivations in any language
 *
 * @property derivations The list of derivations of this word
 *
 * @param parent Identifier used to associate a word to a global equivalent of it (basically the translation of it in english)
 * @param word The word itself in the respective language
 */
class DerivatableWord(
    parent: String,
    word: String,
    val derivations: List<Word> = listOf(),
) : Word(parent, word)