package cc.wordview.gengolex.languages

interface Tokenizer {
    fun tokenize(words: List<String>): ArrayList<String>
    fun initializeDictonary(path: String)
}