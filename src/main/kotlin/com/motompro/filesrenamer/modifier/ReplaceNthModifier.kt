package com.motompro.filesrenamer.modifier

import java.io.File

/**
 * Represents a file name modifier that will replace the nth occurrence of the specified string by the given replacement
 * in file name
 * @param string The specified string
 * @param replacement The string that will replace the old one
 * @param n The occurrence number
 */
class ReplaceNthModifier(
    private val string: String,
    private val replacement: String,
    private val n: Int = 1,
) : Modifier {

    override fun apply(file: File) {
        val oldName = file.nameWithoutExtension
        val nthOccurrenceIndex = Modifier.findNthOccurrenceIndex(oldName, string, n)
        if (nthOccurrenceIndex == -1) return
        val newName = oldName.replaceRange(nthOccurrenceIndex, string.length, replacement)
        Modifier.renameFile(file, newName)
    }
}
