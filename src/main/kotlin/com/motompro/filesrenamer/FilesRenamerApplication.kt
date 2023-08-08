package com.motompro.filesrenamer

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.stage.Stage

private const val DEFAULT_WIDTH = 720.0
private const val DEFAULT_HEIGHT = 480.0
private const val TITLE = "Files Renamer"

class FilesRenamerApplication : Application() {

    init {
        INSTANCE = this
    }

    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(FilesRenamerApplication::class.java.getResource("main-view.fxml"))
        val scene = Scene(fxmlLoader.load(), DEFAULT_WIDTH, DEFAULT_HEIGHT)
        stage.title = TITLE
        stage.scene = scene
        stage.show()
    }

    /**
     * Show an error dialog containing the given message
     * @param title The dialog's title
     * @param message The dialog's content
     */
    fun showErrorAlert(title: String, message: String) {
        val errorAlert = Alert(Alert.AlertType.ERROR)
        errorAlert.title = title
        errorAlert.contentText = message
        errorAlert.showAndWait()
    }

    companion object {
        lateinit var INSTANCE: FilesRenamerApplication
    }
}

fun main() {
    Application.launch(FilesRenamerApplication::class.java)
}
