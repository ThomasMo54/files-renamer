package com.motompro.filesrenamer

import com.google.gson.JsonParser
import javafx.application.Application
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.ButtonBar
import javafx.scene.control.ButtonType
import javafx.scene.image.Image
import javafx.stage.Stage
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

private const val DEFAULT_WIDTH = 720.0
private const val DEFAULT_HEIGHT = 480.0
private val LATEST_RELEASE_URL = URL("https://api.github.com/repos/ThomasMo54/files-renamer/releases/latest")

class FilesRenamerApplication : Application() {

    val version: String? = javaClass.`package`.implementationVersion
    val appIcon = Image(getResourceStream("image/app-icon.png"))

    init {
        INSTANCE = this
    }

    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader()
        val scene = Scene(fxmlLoader.load(getResourceStream("main-view.fxml")), DEFAULT_WIDTH, DEFAULT_HEIGHT)
        stage.title = "$TITLE $version"
        stage.icons.add(appIcon)
        stage.scene = scene
        stage.show()
        thread(start = true) {
            if (version == null) return@thread
            val latestVersion = getLatestVersion() ?: return@thread
            if (version != latestVersion) {
                Platform.runLater { showUpdateAlert(latestVersion) }
            }
        }
    }

    private fun getLatestVersion(): String? {
        // Send API call
        val connection = LATEST_RELEASE_URL.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.connectTimeout = 5000
        connection.readTimeout = 5000
        // Check if the call is a success
        val status = connection.responseCode
        if (status > 299) {
            connection.disconnect()
            return version
        }
        // Read response content
        val reader = BufferedReader(InputStreamReader(connection.inputStream))
        var line: String?
        val content = StringBuffer()
        while (run { line = reader.readLine(); line } != null) content.append(line)
        // Close connection
        connection.disconnect()
        // Parse response content to get tag name
        val jsonObject = JsonParser.parseString(content.toString()).asJsonObject
        return jsonObject["tag_name"].asString.replace("v", "")
    }

    private fun showUpdateAlert(latestVersion: String) {
        val yesButton = ButtonType("Oui", ButtonBar.ButtonData.YES)
        val noButton = ButtonType("Non", ButtonBar.ButtonData.NO)
        val alert = Alert(
            Alert.AlertType.INFORMATION,
            "Une nouvelle version du logiciel est disponible ($latestVersion), voulez-vous l'installer ?",
            yesButton,
            noButton,
        )
        alert.title = "Nouvelle version disponible"
        alert.headerText = "Nouvelle version disponible"
        val result = alert.showAndWait()
        if (result.orElse(noButton) == yesButton) {

        }
    }

    /**
     * Show an error dialog containing the given message
     * @param title The dialog's title
     * @param message The dialog's content
     */
    fun showErrorAlert(title: String, message: String) {
        val errorAlert = Alert(Alert.AlertType.ERROR)
        errorAlert.title = title
        errorAlert.headerText = title
        errorAlert.contentText = message
        errorAlert.showAndWait()
    }

    /**
     * Show an info dialog containing the given message
     * @param title The dialog's title
     * @param message The dialog's content
     */
    fun showInfoAlert(title: String, message: String) {
        val infoAlert = Alert(Alert.AlertType.INFORMATION)
        infoAlert.title = title
        infoAlert.headerText = title
        infoAlert.contentText = message
        infoAlert.showAndWait()
    }

    companion object {
        const val TITLE = "Files Renamer"

        lateinit var INSTANCE: FilesRenamerApplication

        fun getResourceStream(path: String): InputStream? {
            return FilesRenamerApplication::class.java.getResourceAsStream(path)
        }
    }
}
