package cc.wordview.gengolex.languages

import cc.wordview.gengolex.NoDictionaryException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.util.HashMap

interface Tokenizer {
    val dictionary: ArrayList<DerivatableWord>

    fun tokenize(words: List<String>): ArrayList<Word>

    fun initializeDictionary(path: String)

    fun initializeDictionary(path: String, lang: String) {
        val dictionaryDir = File("$path/$lang")

        val files = dictionaryDir.listFiles()?.filter { it.isFile && it.name.endsWith(".json") }

        if (files.isNullOrEmpty())
            throw NoDictionaryException("Unable to find a dictionary for $lang")

        for (file in files) {
            val content = file.inputStream().readBytes().toString(Charsets.UTF_8)

            if (content.isEmpty()) continue

            val typeToken = object : TypeToken<List<DerivatableWord>>() {}.type

            val parsedDictionary = Gson().fromJson<List<DerivatableWord>>(content, typeToken)

            dictionary.addAll(parsedDictionary)
        }
    }

    fun initializeDictionary(dictionaries: HashMap<String, String>)

    fun initializeDictionary(dictionaries: HashMap<String, String>, lang: String) {
        val portugueseDictionary = dictionaries[lang]

        if (portugueseDictionary.isNullOrEmpty())
            throw NoDictionaryException("Unable to find a dictionary for $lang")

        val typeToken = object : TypeToken<List<DerivatableWord>>() {}.type

        val parsedDictionary = Gson().fromJson<List<DerivatableWord>>(portugueseDictionary, typeToken)

        dictionary.addAll(parsedDictionary)
    }
}