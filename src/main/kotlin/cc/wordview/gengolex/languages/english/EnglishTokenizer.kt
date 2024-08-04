package cc.wordview.gengolex.languages.english

import cc.wordview.gengolex.Language
import cc.wordview.gengolex.NoDictionaryException
import cc.wordview.gengolex.languages.GenericTokenizer
import cc.wordview.gengolex.languages.Verb
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.util.HashMap

object EnglishTokenizer : GenericTokenizer() {
    override var dictionary: List<Verb> = listOf()

    override fun initializeDictionary(path: String) {
        val englishDictionaryJson = File("$path/${Language.ENGLISH.name.lowercase()}.json")
            .inputStream()
            .readBytes()
            .toString(Charsets.UTF_8)

        if (englishDictionaryJson.isEmpty())
            throw NoDictionaryException("Unable to find a dictionary for english.")

        val typeToken = object : TypeToken<List<Verb>>() {}.type

        val parsedDictionary = Gson().fromJson<List<Verb>>(englishDictionaryJson, typeToken)

        dictionary = parsedDictionary
    }

    override fun initializeDictionary(dictionaries: HashMap<String, String>) {
        val dictionary = dictionaries["english"]

        if (dictionary.isNullOrEmpty())
            throw NoDictionaryException("Unable to find a dictionary for english.")


        val typeToken = object : TypeToken<List<Verb>>() {}.type

        val parsedDictionary = Gson().fromJson<List<Verb>>(dictionary, typeToken)

        this.dictionary = parsedDictionary
    }
}