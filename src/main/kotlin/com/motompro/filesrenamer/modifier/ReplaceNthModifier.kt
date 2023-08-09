package com.motompro.filesrenamer.modifier

import com.motompro.filesrenamer.FilesRenamerApplication
import com.motompro.filesrenamer.controller.MainController
import com.motompro.filesrenamer.controller.ModifierController
import javafx.scene.Node
import javafx.scene.image.Image
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

    private val icon = Image(FilesRenamerApplication::class.java.getResourceAsStream("./image/replace-text-icon.png"))

    override fun apply(file: File): String {
        require(n > 0)
        val oldName = file.nameWithoutExtension
        val nthOccurrenceIndex = Modifier.findNthOccurrenceIndex(oldName, string, n)
        if (nthOccurrenceIndex == -1) return oldName
        return oldName.replaceRange(nthOccurrenceIndex, string.length, replacement)
    }

    override fun createComponent(mainController: MainController): Node {
        val description = mapOf(
            "Texte" to string,
            "Remplacement" to replacement,
            "Occurrence" to "$n${if (n > 1) "ème" else "ere"}",
        )
        return ModifierController.createModifierComponent("Remplacer texte", icon, description, mainController)
    }
}
