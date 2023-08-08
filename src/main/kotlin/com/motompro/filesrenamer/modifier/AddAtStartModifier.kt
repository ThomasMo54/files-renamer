package com.motompro.filesrenamer.modifier

import java.io.File

/**
 * Represents a file name modifier that will add a specified string at the start of the file name
 * @param string The specified string
 */
class AddAtStartModifier(
    private val string: String,
) : Modifier {

    override fun apply(file: File) {
        val newName = "$string${file.name}"
        Modifier.renameFile(file, newName)
    }
}
