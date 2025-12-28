package com.lotholl.desk.foxlauncher.process

import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.File;

class Process(private val dir: String, private val file: String) {
    private val logger = KotlinLogging.logger {  }

    private val pb = ProcessBuilder(dir + file)
        .directory(File(dir))
        .redirectError(ProcessBuilder.Redirect.INHERIT)
        .redirectOutput(ProcessBuilder.Redirect.INHERIT)

    fun start() {
        logger.info { "Starting process, workid: \"$dir\", file: \"$file\"" }
        pb.start()
    }
}