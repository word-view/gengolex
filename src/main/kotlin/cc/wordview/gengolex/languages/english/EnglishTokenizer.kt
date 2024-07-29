package cc.wordview.gengolex.languages.english

import cc.wordview.gengolex.Language
import cc.wordview.gengolex.languages.GenericTokenizer
import cc.wordview.gengolex.languages.Verb
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

object EnglishTokenizer : GenericTokenizer() {
    override var dictionary: List<Verb> = listOf()

    override fun initializeDictonary(path: String) {
        val englishDictionaryJson = File("$path/${Language.ENGLISH.name.lowercase()}.json")
            .inputStream()
            .readBytes()
            .toString(Charsets.UTF_8)

        val typeToken = object : TypeToken<List<Verb>>() {}.type

        val parsedDictionary = Gson().fromJson<List<Verb>>(englishDictionaryJson, typeToken)

        dictionary = parsedDictionary
    }
}