package com.motompro.filesrenamer.modifier

import com.motompro.filesrenamer.FilesRenamerApplication
import com.motompro.filesrenamer.controller.MainController
import com.motompro.filesrenamer.controller.ModifierController
import javafx.scene.Node
import javafx.scene.image.Image
import java.io.File

/**
 * Represents a file name modifier that will add a specified string at the end of the file name
 * @param string The specified string
 */
class AddAtEndModifier(
    private val string: String,
) : Modifier {

    private val icon = Image(FilesRenamerApplication.getResourceStream("image/add-text-icon.png"))

    override fun apply(file: File): String {
        return "${file.nameWithoutExtension}$string${file.extension}"
    }

    override fun createComponent(mainController: MainController): Node {
        val description = mapOf(
            "Texte" to string,
            "Position" to "Fin",
        )
        return ModifierController.createModifierComponent("Ajouter texte", icon, description, mainController)
    }
}
