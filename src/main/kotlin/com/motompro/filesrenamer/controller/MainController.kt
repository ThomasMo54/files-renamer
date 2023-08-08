package com.motompro.filesrenamer.controller

import com.motompro.filesrenamer.FileRenamer
import com.motompro.filesrenamer.FilesRenamerApplication
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.geometry.Insets
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.ScrollPane
import javafx.scene.input.ScrollEvent
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Text
import javafx.stage.DirectoryChooser
import javafx.stage.Modality
import javafx.stage.Stage

private val MODIFIER_COMPONENT_BACKGROUND = Background(BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY))

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

    val fileRenamer = FileRenamer()

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
        val dialogFxmlLoader = FXMLLoader(FilesRenamerApplication::class.java.getResource("add-modifier-dialog.fxml"))
        val parent = dialogFxmlLoader.load<Parent>()
        val controller = dialogFxmlLoader.getController<AddModifierController>()
        controller.mainController = this

        val scene = Scene(parent, 300.0, 200.0)
        val stage = Stage()
        stage.initModality(Modality.APPLICATION_MODAL)
        stage.title = FilesRenamerApplication.TITLE
        stage.scene = scene
        stage.showAndWait()
    }

    fun updateModifiers() {
        modifierList.children.removeIf { it !is Button }
        fileRenamer.modifiers.reversed().forEach {
            val modifierComponent = it.createComponent(this) as AnchorPane
            modifierComponent.prefWidthProperty().bind(modifierList.widthProperty())
            modifierComponent.background = MODIFIER_COMPONENT_BACKGROUND
            modifierList.children.add(0, modifierComponent)
        }
    }
}
