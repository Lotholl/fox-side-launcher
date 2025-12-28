package com.lotholl.desk.foxlauncher.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.decodeToImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import io.github.oshai.kotlinlogging.KotlinLogging
import java.awt.FileDialog

class FS {
    companion object {
        private val logger = KotlinLogging.logger {}

        fun readResourseAsBytes(path: String) = FS.javaClass.classLoader.getResourceAsStream(path)?.readAllBytes()

        /**
         * Returns a bitmap made from the file on success, and an empty 1x1 bitmap on error
         */
        @Composable
        fun rememberBitmapResource(path: String): Painter {
            return remember(path) {
                val bitmap = readResourseAsBytes(path)?.decodeToImageBitmap() ?: run {
                    logger.info { "Error while loading bitmap resource from java resources: $path" }
                    ImageBitmap(1, 1)
                }
                BitmapPainter(bitmap)
            }
        }

        fun pickFile(header: String = "Pick a file", action: (FileDialog) -> Unit) {
            val picker = FileDialog(ComposeWindow(), header).apply { isVisible = true }
            if (picker.file?.isNotBlank() == true) {
                logger.debug { "Picked file: \"${picker.file}\" in dir: \"${picker.directory}\"" }
                action(picker)
            }
            else logger.debug { "File picker window closed via cancel button" }
        }
    }
}