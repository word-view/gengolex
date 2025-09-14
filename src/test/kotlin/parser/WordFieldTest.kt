package parser

import GengolexTest
import cc.wordview.gengolex.Language
import cc.wordview.gengolex.Parser
import cc.wordview.gengolex.languages.japanese.JapaneseKanjiStrategy
import cc.wordview.gengolex.languages.japanese.JapaneseTokenizer
import kotlin.test.Test
import kotlin.test.assertEquals

class WordFieldTest : GengolexTest() {
    @Test
    fun japanese() {
        JapaneseTokenizer.kanjiStrategy = JapaneseKanjiStrategy.PREFER_DERIVATION

        testWord("走", "走", Language.JAPANESE)
        testWord("僕は走っています", "走っています", Language.JAPANESE)
        testWord("彼が走っています", "走っています", Language.JAPANESE)
        testWord("道で走っています", "走っています", Language.JAPANESE)
        testWord("僕は走ってた", "走ってた", Language.JAPANESE)
        testWord("昨日も走ってた", "走ってた", Language.JAPANESE)
        testWord("友達と走ってた", "走ってた", Language.JAPANESE)
        testWord("僕は走る", "走る", Language.JAPANESE)
        testWord("明日走るつもりだ", "走る", Language.JAPANESE)
        testWord("毎日走るよ", "走る", Language.JAPANESE)
        testWord("私は", "私", Language.JAPANESE)
        testWord("私の家はここです", "私", Language.JAPANESE)
        testWord("私だけが知っている", "私", Language.JAPANESE)
        testWord("雨が降る", "雨", Language.JAPANESE)
        testWord("雨が降っている", "雨", Language.JAPANESE)
        testWord("強い雨が降った", "雨", Language.JAPANESE)
        testWord("変わらないよ", "変わらない", Language.JAPANESE)
        testWord("変わらない景色", "変わらない", Language.JAPANESE)
        testWord("心は変わらない", "変わらない", Language.JAPANESE)
        testWord("近付いたら壊れてしまいそう", "近付いたら", Language.JAPANESE)
        testWord("君に近付いたら嬉しい", "近付いたら", Language.JAPANESE)
        testWord("犬に近付いたら噛まれた", "近付いたら", Language.JAPANESE)
        testWord("お願いが叶うなら", "お願い", Language.JAPANESE)
        testWord("お願いを聞いてくれますか", "お願い", Language.JAPANESE)
        testWord("小さなお願いがあるんだ", "お願い", Language.JAPANESE)
    }

    @Test
    fun portuguese() {
        testWord("Irei correr", "correr", Language.PORTUGUESE)
        testWord("Eu corri", "corri", Language.PORTUGUESE)
        testWord("Estou correndo", "correndo", Language.PORTUGUESE)
        testWord("Corra", "corra", Language.PORTUGUESE)
        testWord("Pegue o guarda-chuva", "guarda-chuva", Language.PORTUGUESE)
    }

    @Test
    fun english() {
        testWord("Run fast", "run", Language.ENGLISH)
        testWord("I am running", "running", Language.ENGLISH)
        testWord("He ran", "ran", Language.ENGLISH)
    }
    

    private fun testWord(text: String, expectedWord: String, lang: Language) {
        val parser = Parser(lang, dictionaries)
        val word = parser.findWords(text).single()
        assertEquals(expectedWord, word.word)
    }
}