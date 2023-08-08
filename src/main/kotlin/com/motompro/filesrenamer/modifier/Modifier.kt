package com.motompro.filesrenamer.modifier

import com.motompro.filesrenamer.controller.MainController
import javafx.scene.Node
import java.io.File
import java.nio.file.FileSystems

/**
 * Represents a file name modifier
 */
interface Modifier {

    /**
     * Apply the transformation to the [File] name. If the transformation can't be applied for some reason, the method
     * will just return without an exception
     * @param file The file that will be renamed
     */
    fun apply(file: File)

    /**
     * Create a modifier component containing the data provided to this modifier
     */
    fun createComponent(mainController: MainController): Node

    companion object {
        /**
         * Rename the given file with the given new name
         * @param file The file
         * @param newName The new name
         */
        fun renameFile(file: File, newName: String) {
            val newFile = File("${file.parent}${FileSystems.getDefault().separator}$newName")
            file.renameTo(newFile)
        }

        /**
         * Find the nth occurrence's index of a pattern in a string
         * @param string The analyzed string
         * @param pattern The pattern to be found
         * @param n The number of occurrence to be found
         * @return The index where the nth occurrence of the pattern has been found, -1 if not found
         */
        fun findNthOccurrenceIndex(string: String, pattern: String, n: Int): Int {
            require(n > 0)
            var index = -1
            for (i in 0 until n) {
                index = string.indexOf(pattern, index + 1)
            }
            return index
        }
    }
}
