package com.lotholl.desk.foxlauncher

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.lotholl.desk.foxlauncher.process.Process
import com.lotholl.desk.foxlauncher.util.FS
import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Fox Side Launcher",
        state = rememberWindowState(height = 800.dp, width = 1280.dp)
    ) {
        val scrollState = rememberScrollState()
        Box {
            Column(Modifier.verticalScroll(scrollState)) {
                Text(
                    "Fox Side Launcher",
                    maxLines = 1,
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.padding(16.dp)
                )
                Image(
                    painter = FS.rememberBitmapResource("why.png"),
                    contentDescription = null,
                    modifier = Modifier.padding(16.dp)
                )
                Row(Modifier.fillMaxWidth().padding(16.dp)) {
                    Text(
                        modifier = Modifier.fillMaxWidth().weight(1f),
                        maxLines = 1,
                        style = MaterialTheme.typography.h4,
                        text = "Launch an app not in the list"
                    )
                    Button(
                        modifier = Modifier.focusable(true).width(80.dp),
                        onClick = {
                            FS.pickFile("App to start") { picker ->
                                val process = Process(picker.directory, picker.file)
                                process.start()
                            }
                        }) {
                        Text("Select")
                    }
                }
                Row(Modifier.fillMaxWidth().padding(16.dp)) {
                    Text(
                        modifier = Modifier.fillMaxWidth().weight(1f),
                        maxLines = 1,
                        style = MaterialTheme.typography.h4,
                        text = "App name 1"
                    )
                    Button(
                        modifier = Modifier.focusable(true).width(80.dp),
                        onClick = {
                            FS.pickFile { picker ->
                                val process = Process(picker.directory, picker.file)
                                process.start()
                            }
                        }) {
                        Text("Start")
                    }
                }

            }
            VerticalScrollbar(adapter = rememberScrollbarAdapter(scrollState), Modifier.align(Alignment.CenterEnd))
        }
    }
}
