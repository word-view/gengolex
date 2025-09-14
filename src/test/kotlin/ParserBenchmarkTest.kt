import cc.wordview.gengolex.Language
import cc.wordview.gengolex.Parser
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.time.Duration
import kotlin.time.measureTimedValue

@Ignore("Run the benchmark separately from the tests suites")
class ParserBenchmarkTest {
    // Benchmark only japanese because it is the most complex one at the moment
    @Test
    fun parseJapanese8chars() {
        val time = calculateAverageExecutionTime {
            val parser = Parser(Language.JAPANESE)
            parser.addDictionary("kanji", kanjiDictionaryJsonString)
            parser.findWords("僕は走っています")
        }

        printRes("parseJapanese8chars", time)
    }

    @Test
    fun parseJapanese19chars() {
        val time = calculateAverageExecutionTime {
            val parser = Parser(Language.JAPANESE)
            parser.addDictionary("kanji", kanjiDictionaryJsonString)
            parser.findWords("昨日の朝、僕は友達と公園で走っています")
        }

        printRes("parseJapanese19chars", time)
    }

    @Test
    fun parseJapanese56chars() {
        val time = calculateAverageExecutionTime {
            val parser = Parser(Language.JAPANESE)
            parser.addDictionary("kanji", kanjiDictionaryJsonString)
            parser.findWords("昨日の夕方、雨が少し降っていたけれど、僕は友達と公園を何周も走っていますし、その後も音楽を聴きながら走っています")
        }

        printRes("parseJapanese56chars", time)
    }

    @Test
    fun parseJapanese233chars() {
        val time = calculateAverageExecutionTime {
            val parser = Parser(Language.JAPANESE)
            parser.addDictionary("kanji", kanjiDictionaryJsonString)
            parser.findWords(
                "昨日の朝早く起きて、朝ご飯を食べてから公園に行き、友達と一緒に準備運動をして、空が少し曇っていたけれど雨は降らなくて、風が気持ちよかったので、僕たちは話しながらゆっくり走り始め、少しずつスピードを上げていき、汗をかきながら笑顔で走っていますが、その途中で犬の散歩をしている人に挨拶をしたり、小さな子供たちが遊んでいるのを見たりして、さらに川沿いの道まで足を伸ばして景色を楽しみながら走っていますし、最後にはベンチに座って水を飲み、今日もいい運動ができたと感じています"
            )
        }

        printRes("parseJapanese233chars", time)
    }

    @Test
    fun parseJapanese512chars() {
        val time = calculateAverageExecutionTime {
            val parser = Parser(Language.JAPANESE)
            parser.addDictionary("kanji", kanjiDictionaryJsonString)
            parser.findWords(
                "静かな夜の街を抜け出して、まだ眠らない夢を探していると、遠くの灯りが揺れて、心の奥に隠していた記憶が蘇り、笑った日も泣いた日もすべて繋がって、僕の背中を押してくれる。君と出会った奇跡を思い出しながら、失いたくないものを胸に抱いて、僕は走っています。たとえ暗闇に包まれても、必ず朝はやってきて、雲の隙間から差し込む光を信じれば、もう一度立ち上がれる。繰り返す日々の中で迷ったとしても、君の声が聞こえるたびに、未来への道を選べると信じて、走っています。 雨が頬を濡らしても、涙と混ざり合っても、心の奥で燃えている小さな炎は決して消えなくて、その炎が僕を導いていく。誰かに笑われても構わない、夢を追いかけるために、僕は今日も走っています。果てしない空の下で、流れる雲を追い越して、希望の歌を胸に響かせながら、走っています。 そして、疲れ果てて立ち止まりそうなときも、君の笑顔を思い出せば、また一歩を踏み出せる。遠く離れていても、心は確かに繋がっていて、見えない絆が強く結んでいるから、僕は明日も走っています。どこまでも続く道の上で、夢と現実の狭間を超えて、傷だらけになりながらも、それでも信じる心を失わず、僕はずっと走っています。"
            )
        }

        printRes("parseJapanese512chars", time)
    }

    @Test
    fun parseJapanese1250chars() {
        val time = calculateAverageExecutionTime {
            val parser = Parser(Language.JAPANESE)
            parser.addDictionary("kanji", kanjiDictionaryJsonString)
            parser.findWords(
                "朝日が昇るころ、街は少しずつ目を覚まし、人々が家を出て仕事や学校へ向かって歩いていく。子供たちは元気に笑いながら走り回り、大人たちは忙しそうに足を速め、信号が青に変わるたびに流れのように動き出す。その中で僕は静かに深呼吸をして、ゆっくりと準備を整え、走るための靴紐を結び直す。体がまだ眠っているように重いと感じても、一歩を踏み出せば次の一歩は自然とついてきて、やがてリズムが生まれる。そうして気付けば僕は走っています。 公園を抜けて川沿いの道に出ると、木々の間から差し込む光が眩しく、風が頬を撫で、鳥の声が遠くから聞こえてくる。走っているとき、頭の中には色々な考えが浮かんでは消えていく。昨日の出来事、これからの予定、叶えたい夢や避けられない不安。けれども足を動かし続けていると、それらは次第に薄れていき、代わりに「今ここにいる」という感覚だけが残る。そうして僕は走っています。汗が背中を流れ、呼吸が荒くなっても、走り続けることがすべてを洗い流してくれるように感じる。 道の途中で犬を散歩している人に出会い、軽く会釈を交わす。自転車に乗った学生たちが僕を追い越していき、遠くからは工事の音や車のクラクションが聞こえる。世界は絶えず動いていて、立ち止まれば置いていかれそうになる。それでも僕は自分の歩幅で、いや、走るリズムで進んでいく。小さな子供が「頑張って」と声をかけてくれると、思わず笑顔になり、さらにスピードを上げる。こうして僕は走っています。 長い直線の道を抜けると、街の雑踏が遠ざかり、代わりに静かな田舎の風景が広がる。稲穂が風に揺れ、遠くの山並みが青く霞み、雲がゆっくりと流れている。都会の喧騒を離れることで、心は不思議と落ち着いていき、自然の中に身を置くことで余計なことは考えなくなる。僕はただ呼吸を感じ、足の動きを感じ、そして「生きている」という実感を覚える。走っています。誰に見せるわけでもなく、記録を残すわけでもなく、ただ自分のために走っている。 それでも時々、立ち止まりたくなる瞬間がある。膝が痛んだり、呼吸が苦しくなったり、心が弱音を吐こうとしたり。それでも足を止めずに進むのは、きっとその先に見える景色を知りたいからだろう。知らない道を抜けたときに出会う景色、予想していなかった瞬間、そういう小さな発見が僕を前へと動かす力になる。だから今日も僕は走っています。どんなに疲れていても、立ち止まる理由よりも、進み続ける理由の方が強い。 太陽が高くなり、汗が滴り落ち、喉が渇いても、走り続けることでしか得られない感覚がある。それは達成感かもしれないし、ただの自己満足かもしれない。けれども、足を動かすたびに確かに「自分は生きている」と思える瞬間があるのだ。だから僕はこれからも走っています。"
            )
        }

        printRes("parseJapanese1250chars", time)
    }

    @Test
    @Ignore("This is only a stress test it's very unlikely that we will have to deal with 10k+ inputs")
    fun parseJapaneseStressTest10kChars() {
        // Generate a large text by repeating a block multiple times
        val block = """
            朝日が昇るころ、街は少しずつ目を覚まし、人々が家を出て仕事や学校へ向かって歩いていく。
            子供たちは元気に笑いながら走り回り、大人たちは忙しそうに足を速め、信号が青に変わるたびに流れのように動き出す。
            その中で僕は静かに深呼吸をして、ゆっくりと準備を整え、走るための靴紐を結び直す。体がまだ眠っているように重いと感じても、一歩を踏み出せば次の一歩は自然とついてきて、やがてリズムが生まれる。
            そうして気付けば僕は走っています。
        """.trimIndent()

        // Repeat the block enough times to reach ~10,000 characters
        val repetitions = 50
        val largeText = block.repeat(repetitions)

        val time = calculateAverageExecutionTime {
            val parser = Parser(Language.JAPANESE)
            parser.addDictionary("kanji", kanjiDictionaryJsonString)
            parser.findWords(largeText)
        }

        printRes("parseJapaneseStressTest", time)
    }

    private fun <T> calculateAverageExecutionTime(block: () -> T): Duration {
        val (_, time1) = measureTimedValue(block)
        val (_, time2) = measureTimedValue(block)
        val (_, time3) = measureTimedValue(block)
        val (_, time4) = measureTimedValue(block)
        val (_, time5) = measureTimedValue(block)

        return (time1+time2+time3+time4+time5) / 5
    }

    private fun printRes(name: String, duration: Duration) {
        print("$name: Average execution time is ${duration.inWholeMilliseconds}ms\n")
    }
}