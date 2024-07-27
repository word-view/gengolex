package cc.wordview.gengolex.portuguese

import cc.wordview.gengolex.WordType

class PortugueseVerb(
    val verb: String,
    val derivations: List<String> = listOf(),
    val type: WordType
)