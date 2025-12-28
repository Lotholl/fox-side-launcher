plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

group = "com.lotholl.desk"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(kotlin("stdlib"))
    implementation(compose.desktop.currentOs)
    implementation(compose.components.resources)
    implementation(compose.material3)
    implementation(compose.ui)
    //logs
    implementation("io.github.oshai:kotlin-logging-jvm:7.0.3")
    implementation("org.slf4j:slf4j-api:2.0.17")
    implementation("ch.qos.logback:logback-classic:1.5.22")
}

tasks.test {
    useJUnitPlatform()
}


compose.desktop {
    application {
        mainClass = "com.lotholl.desk.foxlauncher.LauncherKt"

        // Args to Kotlin main
        args += listOf("--log-level=info")

        // JVM options
        jvmArgs += listOf(
            "-Xms128m",
            "-Xmx512m",
            "-Dfile.encoding=UTF-8"
        )

        nativeDistributions {
            packageName = "steamdeck-launcher"
            packageVersion = "0.1.0"
            description = "Custom launcher for Steam Deck"

            // Simplest: include all JDK modules in the embedded runtime (bigger, but avoids ClassNotFound issues)
            includeAllModules = true

            linux {
                appCategory = "Game"
                // iconFile.set(project.file("icons/launcher.png"))
            }
        }
    }
}

compose.resources {
    publicResClass = false
    packageOfResClass = "resources"
    generateResClass = auto
}

kotlin {
    jvmToolchain(21)
}