package cc.wordview.gengolex.word

class Syntax(
    val default: Specifier?,
    val negative: Specifier?,
    val conditional: Specifier?,
)

class Specifier(val start: Int, val end: Int)