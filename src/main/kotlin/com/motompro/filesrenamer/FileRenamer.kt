package com.motompro.filesrenamer

import com.motompro.filesrenamer.modifier.Modifier
import java.io.File
import java.nio.file.FileSystems

class FileRenamer {

    val files: MutableList<File> = mutableListOf()

    val modifiers: MutableList<Modifier> = mutableListOf()

    val renamePreview: List<File>
        get() {
            val copiedFiles = files.map { File(it.name) }
            return copiedFiles.map { file ->
                var modifiedFile = file
                modifiers.forEach { modifiedFile = File(it.apply(modifiedFile)) }
                modifiedFile
            }
        }

    fun apply() {
        val modifiedFiles = files.map { file ->
            var modifiedFile = file
            modifiers.forEach { modifiedFile = File(it.apply(modifiedFile)) }
            renameFile(file, modifiedFile.name)
            modifiedFile
        }
        files.clear()
        files.addAll(modifiedFiles)
    }

    private fun renameFile(file: File, newName: String) {
        val newFile = File("${file.parent}${FileSystems.getDefault().separator}$newName")
        file.renameTo(newFile)
    }
}
