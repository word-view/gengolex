package cc.wordview.gengolex.languages

import cc.wordview.gengolex.NoDictionaryException
import cc.wordview.gengolex.word.DerivatableWord
import cc.wordview.gengolex.word.Word
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import java.io.File
import java.util.HashMap
import kotlin.collections.emptyList
import kotlin.collections.flatten

interface Tokenizer {
    val dictionary: ArrayList<DerivatableWord>

    companion object {
        private val gson = Gson()
        private val typeToken = object : TypeToken<List<DerivatableWord>>() {}.type
    }

    fun tokenize(words: List<String>): ArrayList<Word>

    fun initializeDictionary(path: String)

    fun initializeDictionary(path: String, lang: String) {
        val dictionaryDir = File("$path/$lang")

        val files = dictionaryDir.listFiles()?.filter { it.isFile && it.name.endsWith(".json") } ?:
            throw NoDictionaryException("Unable to find a dictionary for $lang")

        dictionary.clear()
        // at the moment of this commit the largest dictionary contains 38 words,
        dictionary.ensureCapacity(50)

        runBlocking(Dispatchers.IO) {
            val parsedDictionaries = files.map { file ->
                async {
                    val content = file.inputStream().use { it.readBytes().toString(Charsets.UTF_8) }
                    if (content.isEmpty()) emptyList() else gson.fromJson<List<DerivatableWord>>(content, typeToken)
                }
            }.awaitAll()

            dictionary.addAll(parsedDictionaries.flatten())
        }
    }

    fun initializeDictionary(dictionaries: HashMap<String, String>)

    fun initializeDictionary(dictionaries: HashMap<String, String>, lang: String) {
        val hashmapDictionary = dictionaries[lang] ?: throw NoDictionaryException("Unable to find a dictionary for $lang")

        dictionary.clear()
        // at the moment of this commit the largest dictionary contains 38 words,
        dictionary.ensureCapacity(50)

        val parsedDictionary = gson.fromJson<List<DerivatableWord>>(hashmapDictionary, typeToken)
        dictionary.addAll(parsedDictionary)
    }
}