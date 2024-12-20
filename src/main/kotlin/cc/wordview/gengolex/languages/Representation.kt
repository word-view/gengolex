package cc.wordview.gengolex.languages

/**
 * Indicates how a Word should represent it's meaning. Should be used as:
 * ```kotlin
 * Representation.ILLUSTRATION.name
 * // or
 * Representation.DESCRIPTION.name
 * ```
 * Because enum values are not parsed by Gson it's going to use the actual name as a string instead.
 */
enum class Representation {
    /**
     * Illustrate the Word's meaning using an image/illustration.
     */
    ILLUSTRATION,

    /**
     * Illustrate the Word's meaning using a description of it.
     */
    DESCRIPTION
}