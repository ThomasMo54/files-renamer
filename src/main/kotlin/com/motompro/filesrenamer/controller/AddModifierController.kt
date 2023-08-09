package com.motompro.filesrenamer.controller

import com.motompro.filesrenamer.FilesRenamerApplication
import com.motompro.filesrenamer.controller.modifier.AddTextController
import com.motompro.filesrenamer.controller.modifier.ModifierDialogController
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.stage.Stage

class AddModifierController {

    lateinit var mainController: MainController

    @FXML
    private lateinit var addTextButton: Button

    @FXML
    private lateinit var removeTextButton: Button

    @FXML
    private lateinit var replaceTextButton: Button

    @FXML
    private fun onAddTextButtonClick(event: ActionEvent) {
        switchToCreationScene<AddTextController>(
            "add-text-dialog.fxml",
            (event.source as Node).scene,
            "Ajouter texte",
            300.0,
            300.0,
        )
    }

    @FXML
    private fun onRemoveTextButtonClick(event: ActionEvent) {
        switchToCreationScene<AddTextController>(
            "remove-text-dialog.fxml",
            (event.source as Node).scene,
            "Supprimer texte",
            300.0,
            300.0,
        )
    }

    @FXML
    private fun onReplaceTextButtonClick(event: ActionEvent) {
        switchToCreationScene<AddTextController>(
            "replace-text-dialog.fxml",
            (event.source as Node).scene,
            "Remplacer texte",
            300.0,
            350.0,
        )
    }

    private fun <T : ModifierDialogController> switchToCreationScene(
        fxmlFileName: String,
        scene: Scene,
        title: String,
        width: Double,
        height: Double,
    ) {
        val fxmlLoader = FXMLLoader()
        val parent = fxmlLoader.load<Parent>(FilesRenamerApplication.getResourceStream(fxmlFileName))
        val controller = fxmlLoader.getController<T>()
        controller.mainController = mainController

        scene.root = parent
        val stage = scene.window as Stage
        stage.title = title
        stage.icons.add(FilesRenamerApplication.INSTANCE.appIcon)
        stage.width = width
        stage.height = height
    }
}
