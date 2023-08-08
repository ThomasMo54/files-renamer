package com.motompro.filesrenamer.controller

import com.motompro.filesrenamer.FileRenamer
import com.motompro.filesrenamer.FilesRenamerApplication
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.scene.text.Text
import javafx.stage.DirectoryChooser

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
        val directoryChooser = DirectoryChooser()
        directoryChooser.title = "Sélectionnez un dossier"
        val selectedDir = directoryChooser.showDialog((event.source as Button).scene.window)
        if (!selectedDir.isDirectory) {
            FilesRenamerApplication.INSTANCE.showErrorAlert("Erreur", "Vous n'avez pas sélectionné un dossier")
            return
        }
        val files = selectedDir.listFiles()?.filter { it.isFile } ?: emptyList()
        if (files.isEmpty()) return
        fileList.children.clear()
        fileList.children.addAll(files.map { Text(it.name) })
        fileRenamer.files.clear()
        fileRenamer.files.addAll(files)
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
