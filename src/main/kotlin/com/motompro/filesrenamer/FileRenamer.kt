package com.motompro.filesrenamer

import com.motompro.filesrenamer.modifier.Modifier
import java.io.File

class FileRenamer {

    val files: MutableList<File> = mutableListOf()

    val modifiers: MutableList<Modifier> = mutableListOf()
}
