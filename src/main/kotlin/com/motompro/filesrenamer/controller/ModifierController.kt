package com.motompro.filesrenamer.controller

import com.motompro.filesrenamer.FilesRenamerApplication
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.control.Button
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
    private lateinit var titleText: Text

    @FXML
    private lateinit var descriptionBox: VBox

    @FXML
    private lateinit var upButton: Button

    @FXML
    private lateinit var downButton: Button

    @FXML
    private lateinit var removeButton: Button

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
        fun createModifierComponent(title: String, description: Map<String, String>, mainController: MainController): AnchorPane {
            val fxmlLoader = FXMLLoader(FilesRenamerApplication::class.java.getResource("modifier-component.fxml"))
            val anchorPane = fxmlLoader.load<AnchorPane>()
            val controller = fxmlLoader.getController<ModifierController>()
            controller.title = title
            controller.description = description
            controller.mainController = mainController
            return anchorPane
        }
    }
}
