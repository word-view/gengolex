package cc.wordview.gengolex.languages.japanese

import cc.wordview.gengolex.languages.Verb

interface IJapaneseTokenizer {
    val kanjiDictionary: List<KanjiWord>
    val hiraganaDictionary: List<Verb>
    val katakanaDictionary: List<Verb>

    fun tokenize(words: List<String>): ArrayList<String>
}