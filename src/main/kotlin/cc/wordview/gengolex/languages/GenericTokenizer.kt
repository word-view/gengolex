package cc.wordview.gengolex.languages

/**
 * Represents a strategy for languages that separate
 * words by whitespaces and have only one writing system (e.g. portuguese, english, spanish).
 * Since the algorithm is the same the only thing that
 * is needed to support this kind of language is to define its dictionary.
 */
abstract class GenericTokenizer : Tokenizer {
    abstract val dictionary: List<DerivatableWord>

    override fun tokenize(words: List<String>): ArrayList<Word> {
        val wordsFound = ArrayList<Word>()

        for (word in words) {
            val wordClean = word.replace(",", "").replace(".", "")

            for (langWord in dictionary) {
                // could be null if derivations is not set in the JSON, like {"parent":"walk","word":"andar"}
                @Suppress("SENSELESS_COMPARISON")
                if (langWord.derivations != null) for (derivation in langWord.derivations) {
                    if (wordClean.lowercase() == derivation.word) {
                        wordsFound.add(derivation)
                    }
                }

                if (langWord.word == wordClean.lowercase()) {
                    wordsFound.add(langWord)
                }
            }
        }

        return wordsFound
    }
}