package cc.wordview.gengolex.portuguese

fun portugueseTokenizer(words: List<String>): ArrayList<String> {
    val wordsFound = ArrayList<String>()

    for (word in words) {
        var wordClean = word.replace(",", "").replace(".", "")

        for (portugueseWord in dictionary) {
            if (portugueseWord.verb == wordClean.lowercase()) {
                wordsFound.add(word)
            }

            if (wordClean.lowercase() in portugueseWord.derivations) {
                wordsFound.add(word)
            }
        }
    }

    return wordsFound
}