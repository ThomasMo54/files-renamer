package com.motompro.filesrenamer.modifier

import java.io.File

/**
 * Represents a file name modifier that will remove all occurrences of the specified string in the file name
 * @param string The specified string
 */
class RemoveAllModifier(
    private val string: String,
) : Modifier {

    override fun apply(file: File) {
        val oldName = file.nameWithoutExtension
        val newName = oldName.replace(string, "")
        Modifier.renameFile(file, newName)
    }
}
