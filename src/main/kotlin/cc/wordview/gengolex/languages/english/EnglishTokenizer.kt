package cc.wordview.gengolex.languages.english

import cc.wordview.gengolex.languages.GenericTokenizer
import cc.wordview.gengolex.languages.DerivatableWord
import java.util.HashMap

object EnglishTokenizer : GenericTokenizer() {
    override var dictionary: ArrayList<DerivatableWord> = arrayListOf()

    override fun initializeDictionary(path: String) {
        super.initializeDictionary(path, "english")
    }

    override fun initializeDictionary(dictionaries: HashMap<String, String>) {
        super.initializeDictionary(dictionaries, "english")
    }
}