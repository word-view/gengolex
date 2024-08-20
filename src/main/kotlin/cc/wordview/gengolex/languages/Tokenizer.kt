package cc.wordview.gengolex.languages

import java.util.HashMap

interface Tokenizer {
    fun tokenize(words: List<String>): ArrayList<Word>
    fun initializeDictionary(path: String)
    fun initializeDictionary(dictionaries: HashMap<String, String>)
}