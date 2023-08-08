package com.motompro.filesrenamer.modifier

import com.motompro.filesrenamer.controller.MainController
import com.motompro.filesrenamer.controller.ModifierController
import javafx.scene.Node
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
        require(index > 0)
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

    override fun createComponent(mainController: MainController): Node {
        val description = mapOf(
            "Texte" to string,
            "Position" to index.toString(),
        )
        return ModifierController.createModifierComponent("Ajouter texte", description, mainController)
    }
}
