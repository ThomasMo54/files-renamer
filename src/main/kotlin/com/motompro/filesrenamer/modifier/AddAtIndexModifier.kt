package com.motompro.filesrenamer.modifier

import java.io.File

/**
 * Represents a file name modifier that will add a specified string at a specified index in the file name
 * @param index The index
 * @param string The specified string
 */
class AddAtIndexModifier(
    private val index: Int,
    private val string: String,
) : Modifier {

    override fun apply(file: File) {
        val oldName = file.nameWithoutExtension
        if (oldName.length <= index) {
            val newName = "$oldName$string${file.extension}"
            Modifier.renameFile(file, newName)
            return
        }
        val firstPart = oldName.substring(0, index)
        val lastPart = oldName.substring(index)
        val newName = "$firstPart$string$lastPart${file.extension}"
        Modifier.renameFile(file, newName)
    }
}
