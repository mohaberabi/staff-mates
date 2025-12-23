rootProject.name = "EGroupStaffMate"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}
includeBuild("build-logic")

include(":composeApp")
include(":parser")
include(":erabitime")
include(":calculations")
include(":preferences")
include(":utility")
include(":modelhub")
include(":core")
include(":network")
include(":syncfromserver")
include(":synctoserver")
include(":database")
include(":uihub")
include(":papergen")
include(":nfc")
include(":features")
include(":navigation")















