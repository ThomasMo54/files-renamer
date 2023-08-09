package com.motompro.filesrenamer.modifier

import com.motompro.filesrenamer.controller.MainController
import com.motompro.filesrenamer.controller.ModifierController
import javafx.scene.Node
import java.io.File

/**
 * Represents a file name modifier that will remove all occurrences of the specified string in the file name
 * @param string The specified string
 */
class RemoveAllModifier(
    private val string: String,
) : Modifier {

    override fun apply(file: File): String {
        val oldName = file.nameWithoutExtension
        return oldName.replace(string, "")
    }

    override fun createComponent(mainController: MainController): Node {
        val description = mapOf(
            "Texte" to string,
            "Occurrence" to "Toutes",
        )
        return ModifierController.createModifierComponent("Supprimer texte", description, mainController)
    }
}
