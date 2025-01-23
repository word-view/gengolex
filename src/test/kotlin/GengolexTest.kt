open class GengolexTest {
    protected val dictionaries =
        object {}.javaClass.classLoader.getResource("")?.toString()?.replace("file:", "") + "/dictionaries/"
}