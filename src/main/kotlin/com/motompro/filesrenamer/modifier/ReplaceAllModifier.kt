package com.motompro.filesrenamer.modifier

import com.motompro.filesrenamer.FilesRenamerApplication
import com.motompro.filesrenamer.controller.MainController
import com.motompro.filesrenamer.controller.ModifierController
import javafx.scene.Node
import javafx.scene.image.Image
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

    private val icon = Image(FilesRenamerApplication::class.java.getResourceAsStream("./image/replace-text-icon.png"))

    override fun apply(file: File): String {
        val oldName = file.nameWithoutExtension
        return oldName.replace(string, replacement)
    }

    override fun createComponent(mainController: MainController): Node {
        val description = mapOf(
            "Texte" to string,
            "Remplacement" to replacement,
            "Occurrence" to "Toutes",
        )
        return ModifierController.createModifierComponent("Remplacer texte", icon, description, mainController)
    }
}
