package com.motompro.filesrenamer.controller.modifier

import com.motompro.filesrenamer.FilesRenamerApplication
import com.motompro.filesrenamer.controller.MainController
import com.motompro.filesrenamer.modifier.ReplaceAllModifier
import com.motompro.filesrenamer.modifier.ReplaceNthModifier
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import javafx.stage.Stage

class ReplaceTextController : ModifierDialogController {

    override lateinit var mainController: MainController

    @FXML
    private lateinit var textInput: TextField

    @FXML
    private lateinit var replacementInput: TextField

    @FXML
    private lateinit var allCheckBox: CheckBox

    @FXML
    private lateinit var occurrenceInput: TextField

    @FXML
    private lateinit var validateButton: Button

    @FXML
    private fun initialize() {
        occurrenceInput.textProperty().addListener { _, _, newValue ->
            if (!newValue.matches(Regex("\\d*"))) {
                occurrenceInput.text = newValue.replace(Regex("\\D"), "")
            }
        }
    }

    @FXML
    private fun onAllCheckBoxClick(event: ActionEvent) {
        occurrenceInput.isDisable = allCheckBox.isSelected
        occurrenceInput.text = ""
    }

    @FXML
    private fun onValidateButtonClick(event: ActionEvent) {
        val textToReplace = textInput.text
        val replacement = replacementInput.text
        val window = (event.source as Node).scene.window as Stage
        if (textToReplace.isBlank()) {
            window.close()
            return
        }
        val modifier = if (allCheckBox.isSelected) {
            ReplaceAllModifier(textToReplace, replacement)
        } else {
            if (occurrenceInput.text.isBlank()) {
                FilesRenamerApplication.INSTANCE.showErrorAlert("Erreur", "Veuillez préciser une occurrence")
                return
            }
            ReplaceNthModifier(textToReplace, replacement, occurrenceInput.text.toInt())
        }
        mainController.fileRenamer.modifiers.add(modifier)
        mainController.updateModifiers()
        window.close()
    }
}
