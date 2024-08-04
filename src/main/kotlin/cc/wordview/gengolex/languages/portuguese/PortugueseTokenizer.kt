package cc.wordview.gengolex.languages.portuguese

import cc.wordview.gengolex.Language
import cc.wordview.gengolex.NoDictionaryException
import cc.wordview.gengolex.languages.GenericTokenizer
import cc.wordview.gengolex.languages.Verb
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.util.HashMap

object PortugueseTokenizer : GenericTokenizer() {
    override var dictionary: List<Verb> = listOf()

    override fun initializeDictionary(path: String) {
        val portugueseDictionaryJson = File("$path/${Language.PORTUGUESE.name.lowercase()}.json")
            .inputStream()
            .readBytes()
            .toString(Charsets.UTF_8)

        if (portugueseDictionaryJson.isEmpty())
            throw NoDictionaryException("Unable to find a dictionary for portuguese")

        val typeToken = object : TypeToken<List<Verb>>() {}.type

        val parsedDictionary = Gson().fromJson<List<Verb>>(portugueseDictionaryJson, typeToken)

        dictionary = parsedDictionary
    }

    override fun initializeDictionary(dictionaries: HashMap<String, String>) {
        val portugueseDictionary = dictionaries["portuguese"]

        if (portugueseDictionary.isNullOrEmpty())
            throw NoDictionaryException("Unable to find a dictionary for portuguese")

        val typeToken = object : TypeToken<List<Verb>>() {}.type

        val parsedDictionary = Gson().fromJson<List<Verb>>(portugueseDictionary, typeToken)

        dictionary = parsedDictionary

    }
}