package cc.wordview.gengolex.english

import cc.wordview.gengolex.WordType

class EnglishVerb(
    val verb: String,
    val derivations: List<String> = listOf(),
    val type: WordType
)