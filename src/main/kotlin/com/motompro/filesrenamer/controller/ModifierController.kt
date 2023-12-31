package com.motompro.filesrenamer.controller

import com.motompro.filesrenamer.FilesRenamerApplication
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.scene.text.Text

class ModifierController {

    var title: String = ""
        set(value) {
            titleText.text = value
            field = value
        }
    var icon: Image? = null
        set(value) {
            iconImage.image = value
            field = value
        }
    var description: Map<String, String> = emptyMap()
        set(value) {
            value.forEach { (name, value) ->
                descriptionBox.children.add(Text("$name : $value"))
            }
            field = value
        }
    lateinit var mainController: MainController
    private val childAmount: Int
        get() = (parentPane.parent as Pane).children.size
    private val childIndex by lazy { (parentPane.parent as Pane).children.indexOf(parentPane) }

    @FXML
    private lateinit var parentPane: AnchorPane

    @FXML
    private lateinit var iconImage: ImageView

    @FXML
    private lateinit var titleText: Text

    @FXML
    private lateinit var descriptionBox: VBox

    @FXML
    private lateinit var upButtonImage: ImageView

    @FXML
    private lateinit var downButtonImage: ImageView

    @FXML
    private lateinit var removeButtonImage: ImageView

    @FXML
    private fun initialize() {
        upButtonImage.image = Image(FilesRenamerApplication.getResourceStream("image/arrow-up-icon.png"))
        downButtonImage.image = Image(FilesRenamerApplication.getResourceStream("image/arrow-down-icon.png"))
        removeButtonImage.image = Image(FilesRenamerApplication.getResourceStream("image/remove-icon.png"))
    }

    @FXML
    private fun onUpButtonClick(event: ActionEvent) {
        if (childIndex == 0) return
        moveComponent(-1)
    }

    @FXML
    private fun onDownButtonClick(event: ActionEvent) {
        if (childIndex == childAmount - 2) return
        moveComponent(1)
    }

    private fun moveComponent(amount: Int) {
        val modifier = mainController.fileRenamer.modifiers[childIndex]
        mainController.fileRenamer.modifiers.removeAt(childIndex)
        mainController.fileRenamer.modifiers.add(childIndex + amount, modifier)
        mainController.updateModifiers()
    }

    @FXML
    private fun onRemoveButtonClick(event: ActionEvent) {
        mainController.fileRenamer.modifiers.removeAt(childIndex)
        mainController.updateModifiers()
    }

    companion object {
        fun createModifierComponent(title: String, icon: Image, description: Map<String, String>, mainController: MainController): AnchorPane {
            val fxmlLoader = FXMLLoader()
            val anchorPane = fxmlLoader.load<AnchorPane>(FilesRenamerApplication.getResourceStream("modifier-component.fxml"))
            val controller = fxmlLoader.getController<ModifierController>()
            controller.title = title
            controller.icon = icon
            controller.description = description
            controller.mainController = mainController
            return anchorPane
        }
    }
}
