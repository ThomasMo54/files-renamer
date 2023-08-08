package com.motompro.filesrenamer.modifier

import com.motompro.filesrenamer.controller.MainController
import com.motompro.filesrenamer.controller.ModifierController
import javafx.scene.Node
import java.io.File

/**
 * Represents a file name modifier that will remove the nth occurrence of the specified string in the file name
 * @param string The specified string
 * @param n The occurrence number
 */
class RemoveNthModifier(
    private val string: String,
    private val n: Int = 1,
) : Modifier {

    override fun apply(file: File) {
        require(n > 0)
        val oldName = file.nameWithoutExtension
        val nthOccurrenceIndex = Modifier.findNthOccurrenceIndex(oldName, string, n)
        if (nthOccurrenceIndex == -1) return
        val newName = oldName.replaceRange(nthOccurrenceIndex, string.length, "")
        Modifier.renameFile(file, newName)
    }

    override fun createComponent(mainController: MainController): Node {
        val description = mapOf(
            "Texte" to string,
            "Occurrence" to "$n${if (n > 1) "ème" else "ere"}",
        )
        return ModifierController.createModifierComponent("Supprimer texte", description, mainController)
    }
}
