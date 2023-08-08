package com.motompro.filesrenamer.controller

import com.motompro.filesrenamer.FileRenamer
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.layout.VBox

class MainController {

    @FXML
    private lateinit var openButton: Button
    @FXML
    private lateinit var visualizeButton: Button
    @FXML
    private lateinit var applyButton: Button
    @FXML
    private lateinit var addModifierButton: Button

    @FXML
    private lateinit var fileList: VBox
    @FXML
    private lateinit var modifierList: VBox

    private val fileRenamer = FileRenamer()

    @FXML
    private fun onOpenButtonClick(event: ActionEvent) {

    }

    @FXML
    private fun onVisualizeButtonClick(event: ActionEvent) {

    }

    @FXML
    private fun onApplyButtonClick(event: ActionEvent) {

    }

    @FXML
    private fun onAddModifierButtonClick(event: ActionEvent) {

    }
}
