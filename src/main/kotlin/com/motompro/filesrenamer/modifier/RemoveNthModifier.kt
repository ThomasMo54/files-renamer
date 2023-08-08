package com.motompro.filesrenamer.modifier

import java.io.File

/**
 * Represents a file name modifier that will remove the nth occurrence of the specified string in the file name
 * @param string The specified string
 * @param n The occurrence number
 */
class RemoveNthModifier(
    private val string: String,
    private val n: Int = 1,
) : Modifier {

    override fun apply(file: File) {
        val oldName = file.nameWithoutExtension
        val nthOccurrenceIndex = Modifier.findNthOccurrenceIndex(oldName, string, n)
        if (nthOccurrenceIndex == -1) return
        val newName = oldName.replaceRange(nthOccurrenceIndex, string.length, "")
        Modifier.renameFile(file, newName)
    }
}
