package com.motompro.filesrenamer.modifier

import com.motompro.filesrenamer.FilesRenamerApplication
import com.motompro.filesrenamer.controller.MainController
import com.motompro.filesrenamer.controller.ModifierController
import javafx.scene.Node
import javafx.scene.image.Image
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

    private val icon = Image(FilesRenamerApplication::class.java.getResourceAsStream("./image/add-text-icon.png"))

    override fun apply(file: File): String {
        require(index > 0)
        val oldName = file.nameWithoutExtension
        if (oldName.length <= index) {
            return "$oldName$string${file.extension}"
        }
        val firstPart = oldName.substring(0, index)
        val lastPart = oldName.substring(index)
        return "$firstPart$string$lastPart${file.extension}"
    }

    override fun createComponent(mainController: MainController): Node {
        val description = mapOf(
            "Texte" to string,
            "Position" to index.toString(),
        )
        return ModifierController.createModifierComponent("Ajouter texte", icon, description, mainController)
    }
}
