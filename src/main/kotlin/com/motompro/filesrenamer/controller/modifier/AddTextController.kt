package com.motompro.filesrenamer.controller.modifier

import com.motompro.filesrenamer.FilesRenamerApplication
import com.motompro.filesrenamer.controller.MainController
import com.motompro.filesrenamer.modifier.AddAtEndModifier
import com.motompro.filesrenamer.modifier.AddAtIndexModifier
import com.motompro.filesrenamer.modifier.AddAtStartModifier
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.control.CheckBox
import javafx.scene.control.RadioButton
import javafx.scene.control.TextField
import javafx.scene.control.ToggleGroup
import javafx.stage.Stage

class AddTextController : ModifierDialogController {

    override lateinit var mainController: MainController

    private val positionToggleGroup = ToggleGroup()

    @FXML
    private lateinit var textInput: TextField

    @FXML
    private lateinit var positionInput: TextField

    @FXML
    private lateinit var exactPositionCheckBox: CheckBox

    @FXML
    private lateinit var startRadioButton: RadioButton

    @FXML
    private lateinit var endRadioButton: RadioButton

    @FXML
    private lateinit var validateButton: Button

    @FXML
    private fun initialize() {
        startRadioButton.toggleGroup = positionToggleGroup
        endRadioButton.toggleGroup = positionToggleGroup

        positionInput.textProperty().addListener { _, _, newValue ->
            if (!newValue.matches(Regex("\\d*"))) {
                positionInput.text = newValue.replace(Regex("\\D"), "")
            }
        }
    }

    @FXML
    private fun onExactPositionCheckBoxClick(event: ActionEvent) {
        positionInput.isDisable = !exactPositionCheckBox.isSelected
        positionInput.text = ""
    }

    @FXML
    private fun onValidateButtonClick(event: ActionEvent) {
        val textToAdd = textInput.text
        val window = (event.source as Node).scene.window as Stage
        if (textToAdd.isBlank()) {
            window.close()
            return
        }
        val modifier = if (exactPositionCheckBox.isSelected) {
            if (positionInput.text.isBlank()) {
                FilesRenamerApplication.INSTANCE.showErrorAlert("Erreur", "Veuillez préciser une position")
                return
            }
            AddAtIndexModifier(positionInput.text.toInt(), textToAdd)
        } else {
            if (startRadioButton.isSelected) AddAtStartModifier(textToAdd) else AddAtEndModifier(textToAdd)
        }
        mainController.fileRenamer.modifiers.add(modifier)
        mainController.updateModifiers()
        window.close()
    }
}
