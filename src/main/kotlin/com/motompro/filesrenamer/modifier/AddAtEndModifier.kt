package com.motompro.filesrenamer.modifier

import com.motompro.filesrenamer.controller.MainController
import com.motompro.filesrenamer.controller.ModifierController
import javafx.scene.Node
import java.io.File

/**
 * Represents a file name modifier that will add a specified string at the end of the file name
 * @param string The specified string
 */
class AddAtEndModifier(
    private val string: String,
) : Modifier {

    override fun apply(file: File) {
        val newName = "${file.nameWithoutExtension}$string${file.extension}"
        Modifier.renameFile(file, newName)
    }

    override fun createComponent(mainController: MainController): Node {
        val description = mapOf(
            "Texte" to string,
            "Position" to "Fin",
        )
        return ModifierController.createModifierComponent("Ajouter texte", description, mainController)
    }
}
