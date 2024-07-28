package cc.wordview.gengolex.languages.english

import cc.wordview.gengolex.languages.GenericTokenizer
import cc.wordview.gengolex.languages.Verb

object EnglishTokenizer : GenericTokenizer() {
    override val dictionary = listOf(
        Verb("run", listOf("running", "ran"))
    )
}