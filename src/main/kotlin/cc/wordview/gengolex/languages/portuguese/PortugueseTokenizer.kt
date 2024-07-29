package cc.wordview.gengolex.languages.portuguese

import cc.wordview.gengolex.Language
import cc.wordview.gengolex.languages.GenericTokenizer
import cc.wordview.gengolex.languages.Verb
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

object PortugueseTokenizer : GenericTokenizer() {
    override var dictionary: List<Verb> = listOf()

    override fun initializeDictonary(path: String) {
        val portugueseDictionaryJson = File("$path/${Language.PORTUGUESE.name.lowercase()}.json")
            .inputStream()
            .readBytes()
            .toString(Charsets.UTF_8)

        val typeToken = object : TypeToken<List<Verb>>() {}.type

        val parsedDictionary = Gson().fromJson<List<Verb>>(portugueseDictionaryJson, typeToken)

        dictionary = parsedDictionary
    }
}