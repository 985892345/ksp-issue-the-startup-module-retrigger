pluginManagement {
  repositories {
    mavenLocal()
    mavenCentral()
    google()
    gradlePluginPortal()
    // mavenCentral 快照仓库
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
  }
}
plugins {
  id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "ksp-issue-the-startup-module-retrigger"
include("module-startup")
include("module-other")
include("compile-ksp")
