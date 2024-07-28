package cc.wordview.gengolex.languages

interface Tokenizer {
    /**
     * Dictionary containing words of a given language.
     */
    val dictionary: List<Verb>
    fun tokenize(words: List<String>): ArrayList<String>
}