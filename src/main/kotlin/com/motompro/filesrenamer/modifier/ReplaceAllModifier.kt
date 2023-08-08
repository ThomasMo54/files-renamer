package com.motompro.filesrenamer.modifier

import java.io.File

/**
 * Represents a file name modifier that will replace all occurrences of the specified string by the given replacement
 * in the file name
 * @param string The specified string
 * @param replacement The string that will replace the old one
 */
class ReplaceAllModifier(
    private val string: String,
    private val replacement: String,
) : Modifier {

    override fun apply(file: File) {
        val oldName = file.nameWithoutExtension
        val newName = oldName.replace(string, replacement)
        Modifier.renameFile(file, newName)
    }
}