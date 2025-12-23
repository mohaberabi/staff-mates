package com.erabipt.convention.common.extensions

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.getByType


val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun VersionCatalog.getLibrary(alias: String) = findLibrary(alias).get()

fun DependencyHandlerScope.commonMainApi(
    dep: Any,
) {
    "commonMainApi"(dep)
}

fun DependencyHandlerScope.commonMainImplementation(
    dep: Any,
) {
    "commonMainImplementation"(dep)
}

fun DependencyHandlerScope.commonTestImplementation(
    dep: Any,
) {
    "commonTestImplementation"(dep)
}