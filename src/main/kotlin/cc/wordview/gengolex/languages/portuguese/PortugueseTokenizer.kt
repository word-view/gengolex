package cc.wordview.gengolex.languages.portuguese

import cc.wordview.gengolex.languages.GenericTokenizer
import cc.wordview.gengolex.languages.Verb

object PortugueseTokenizer : GenericTokenizer() {
    override val dictionary = listOf(
        Verb("correr", listOf("corri", "correndo", "corra"))
    )
}