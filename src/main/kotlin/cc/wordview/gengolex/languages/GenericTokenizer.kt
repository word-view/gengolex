package cc.wordview.gengolex.languages

/**
 * Represents a strategy for languages that separate
 * words by whitespaces (e.g. portuguese, english, spanish).
 * Since the algorithm is virtually the same the only thing that
 * is needed to support this kind of language is to define its dictionary.
 */
abstract class GenericTokenizer : Tokenizer {
    abstract override val dictionary: List<Verb>

    override fun tokenize(words: List<String>): ArrayList<String> {
        val wordsFound = ArrayList<String>()

        for (word in words) {
            val wordClean = word.replace(",", "").replace(".", "")

            for (langWord in dictionary) {
                if (langWord.verb == wordClean.lowercase()) {
                    wordsFound.add(wordClean)
                }

                if (wordClean.lowercase() in langWord.derivations) {
                    wordsFound.add(wordClean)
                }
            }
        }

        return wordsFound
    }
}