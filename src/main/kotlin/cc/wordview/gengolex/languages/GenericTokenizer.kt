package cc.wordview.gengolex.languages

/**
 * Represents a strategy for languages that separate
 * words by whitespaces and have only one writing system (e.g. portuguese, english, spanish).
 * Since the algorithm is the same the only thing that
 * is needed to support this kind of language is to define its dictionary.
 */
abstract class GenericTokenizer : Tokenizer {
    abstract val dictionary: List<DerivatableWord>

    @Suppress("USELESS_ELVIS")
    private val wordMap: Map<String, Word> by lazy {
        dictionary
            .flatMap { listOf(it) + (it.derivations ?: emptyList()) }
            .associateBy { it.word.lowercase() }
    }

    override fun tokenize(words: List<String>): ArrayList<Word> {
        val wordsFound = ArrayList<Word>()
        for (word in words) {
            val wordClean = word.replace("[,.]".toRegex(), "").lowercase()
            wordMap[wordClean]?.let { wordsFound.add(it) }
        }
        return wordsFound
    }
}