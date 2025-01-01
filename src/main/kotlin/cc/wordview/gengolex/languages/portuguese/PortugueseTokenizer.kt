package cc.wordview.gengolex.languages.portuguese

import cc.wordview.gengolex.languages.GenericTokenizer
import cc.wordview.gengolex.languages.DerivatableWord
import java.util.HashMap

object PortugueseTokenizer : GenericTokenizer() {
    override var dictionary: ArrayList<DerivatableWord> = arrayListOf()

    override fun initializeDictionary(path: String) {
       super.initializeDictionary(path, "portuguese")
    }

    override fun initializeDictionary(dictionaries: HashMap<String, String>) {
        super.initializeDictionary(dictionaries, "portuguese")
    }
}