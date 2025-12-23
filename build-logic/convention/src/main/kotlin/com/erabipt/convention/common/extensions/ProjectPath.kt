package com.erabipt.convention.common.extensions

import org.gradle.api.Project
import java.util.Locale

fun Project.pathToPackage(): String {
    val relativePackageName = path
        .replace(':', '.')
        .lowercase()
    return "com.staffmate${relativePackageName}"
}


fun Project.pathToNativeFrameWork(): String {
    val parts = path.split(":", "-", "_", " ")
    val result = parts.joinToString("") { part ->
        part.replaceFirstChar {
            it.titlecase(Locale.ROOT)
        }
    }
    return result
}

fun Project.pathToResPrefix(): String = path
    .replace(':', '_')
    .lowercase()
    .drop(1) + "_"